package jp.co.sss.cytech.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.cytech.repository.CartsRepository;

@Controller
public class PurchaseCompleteController {

	@Autowired
	CartsRepository cartsRepository;
	
	@RequestMapping(value = "/user/purchaseProcess", params = "purchase")
	public String showPurchaseComplete(@RequestParam(name="cartId", required = false) Integer cartId,HttpSession session,Model model) {
	    // 購入確定処理

		model.addAttribute("imagePath", session.getAttribute("imagePath"));
		model.addAttribute("productName", session.getAttribute("productName"));
		model.addAttribute("quantity", session.getAttribute("quantity"));
		model.addAttribute("price", session.getAttribute("price"));
		model.addAttribute("fullAddress", session.getAttribute("fullAddress"));
		System.out.println("PurchaseCompleteController:"+cartId);
		if(cartId != null) {
			cartsRepository.deleteById(cartId);
		}
		
	    return "/user/purchaseComplete";
	}

	

}
