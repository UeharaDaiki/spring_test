package jp.co.sss.cytech.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.PurchaseDetailForm;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class PurchaseDetailController {
	@Autowired
	UserRepository userRepository;

	
	@RequestMapping(path = "/user/purchaseDetail")
	public String showPurchaseDetail(@RequestParam(name="productName", required = false) String productName,
			@RequestParam(name="price", required = false) Integer price,
			@RequestParam(name="quantity", required = false) Integer quantity,
			@RequestParam(name="imagePath", required = false) String imagePath,
			@RequestParam(name="cartId", required = false) Integer cartId,
			HttpSession session,Model model) {
		if(productName != null) {
			session.setAttribute("productName", productName);
			session.setAttribute("price", price);
			session.setAttribute("quantity", quantity);
			session.setAttribute("imagePath", imagePath);
		}
		else {
            productName = (String) session.getAttribute("productName");
            price = (Integer) session.getAttribute("price");
            quantity = (Integer) session.getAttribute("quantity");
            imagePath = (String) session.getAttribute("imagePath");
        }
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> loginUser= userRepository.findByEmail(email);
	    User user = loginUser.get();
	    String userName = user.getUserName();
	    model.addAttribute("userName", userName);
	    model.addAttribute("price", price);
	    model.addAttribute("quantity", quantity);
	    model.addAttribute("productName", productName);
	    model.addAttribute("cartId", cartId);


	return "user/purchaseDetail";
	}
	
	@RequestMapping(path = "/user/purchaseDetail", method = RequestMethod.POST)
	public String confirm(@RequestParam(name="cartId", required = false) Integer cartId,PurchaseDetailForm purchaseDetailForm,Model model,HttpSession session) {
	    //セッションから取得
	    String productName = purchaseDetailForm.getProductName();
	    Integer price = purchaseDetailForm.getPrice();
	    Integer quantity = purchaseDetailForm.getQuantity();
	    
	    //ログイン情報からユーザー名の取得
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> loginUser= userRepository.findByEmail(email);
	    User user = loginUser.get();
	    String userName = user.getUserName();
	    
	    //ラジオボタン選択の分岐
	    if ("1".equals(purchaseDetailForm.getAddressChoice())) {
			model.addAttribute("address", purchaseDetailForm.getAddress1());
			model.addAttribute("building",purchaseDetailForm.getBuilding1());
			session.setAttribute("fullAddress", purchaseDetailForm.getAddress1().concat(purchaseDetailForm.getBuilding1()));
		} else {
			model.addAttribute("address", purchaseDetailForm.getAddress2());
			model.addAttribute("building",purchaseDetailForm.getBuilding2());
			session.setAttribute("fullAddress", purchaseDetailForm.getAddress2().concat(purchaseDetailForm.getBuilding2()));
		}
		
		if ("2".equals(purchaseDetailForm.getCardChoice())) {
			model.addAttribute("cardNumber", purchaseDetailForm.getCardNumber1());
			model.addAttribute("cardExpire",purchaseDetailForm.getCardExpire1());
		} else {
			model.addAttribute("cardNumber", purchaseDetailForm.getCardNumber1());
			model.addAttribute("cardExpire",purchaseDetailForm.getCardExpire1());
		}
		
		Integer totalPrice = price * quantity;
		
		//modelにセット
		model.addAttribute("userName", userName);
		model.addAttribute("productName", productName);
		model.addAttribute("price", price);
		model.addAttribute("quantity", quantity);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("cartId", cartId);

	return "user/purchaseConfirm";
	}
}
