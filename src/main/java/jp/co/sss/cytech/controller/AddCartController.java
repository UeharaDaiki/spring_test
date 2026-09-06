package jp.co.sss.cytech.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.cytech.entity.Carts;
import jp.co.sss.cytech.entity.Products;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.CartsRepository;
import jp.co.sss.cytech.repository.ProductsRepository;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class AddCartController {
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	CartsRepository cartsRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path = "/user/addCart")
	public String showAddCart(@RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity, Model model,HttpSession session) {
		
		session.setAttribute("quantity",quantity);
		Products product = productsRepository.findById(productId).get();
		Integer price = product.getIncludeTax();
		Integer totalPrice = price * quantity;
		String productName = product.getProductName();
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> loginUser= userRepository.findByEmail(email);
	    Integer userId = loginUser.get().getUserId();
		Optional<Carts> cartInfo = cartsRepository.findByUserIdAndProductId(userId, productId);
		

		
		Carts cart = new Carts();
		if(cartInfo.isPresent()) {
			cart = cartInfo.get();
			int newQuantity = cart.getQuantity() + quantity;
			cart.setQuantity(newQuantity);
			Integer cartId = cartInfo.get().getCartId();
			model.addAttribute("cartId", cartId);
			cartsRepository.save(cart);
		}else {
			System.out.println(userId);
			cart.setUserId(userId);
			cart.setProductId(productId);
			cart.setQuantity(quantity);
			Carts savedCart = cartsRepository.save(cart);
			Integer cartId = savedCart.getCartId();
			model.addAttribute("cartId", cartId);
		}
		
		model.addAttribute("quantity",quantity);
		model.addAttribute("product",product);
		model.addAttribute("totalPrice",totalPrice);
		model.addAttribute("price",price);
		model.addAttribute("productName", productName);

	return "user/addCart";
	}
}
