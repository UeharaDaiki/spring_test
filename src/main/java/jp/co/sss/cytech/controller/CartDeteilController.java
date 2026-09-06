package jp.co.sss.cytech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.cytech.entity.Carts;
import jp.co.sss.cytech.entity.Products;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.CartDetailForm;
import jp.co.sss.cytech.repository.CartsRepository;
import jp.co.sss.cytech.repository.ProductsRepository;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class CartDeteilController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartsRepository cartsRepository;
	@Autowired
	ProductsRepository productsRepository;
	
	
	@RequestMapping(path = "/user/cartDeteil")
	public String showCartDeteil(CartDetailForm cartDetailForm,Model model) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> loginUser= userRepository.findByEmail(email);
	    User user = loginUser.get();
	    Integer userId = user.getUserId();
	    List<Carts> loginUserCart = cartsRepository.findByUserId(userId);
	    List<CartDetailForm> cartList = new ArrayList<>();
	    for(Carts cart: loginUserCart) {
	    	Products product = productsRepository.findById(cart.getProductId()).get();
	    	CartDetailForm form = new CartDetailForm();
	    	form.setIncludeTax(product.getIncludeTax());
	    	form.setPrice(product.getPrice());
	    	form.setProductName(product.getProductName());
	    	form.setQuantity(cart.getQuantity());
	    	form.setImagePath(product.getImgPath());
	    	form.setStock(product.getStock());
	    	form.setTotalPrice(cart.getQuantity() * product.getPrice());
	    	form.setTotalIncludeTax(cart.getQuantity() * product.getIncludeTax());
	    	form.setCartId(cart.getCartId());
	    	cartList.add(form);
	    }
	    Integer cartTotalPrice = 0;
	    Integer cartTotalQuantity = 0;
	    Integer cartTotalIncludeTax = 0;
	    //カートの合計個数
	    for(Carts cart: loginUserCart) {
	    	cartTotalQuantity += cart.getQuantity();
	    }
	    
	    //カート内合計金額
	    for(Carts cart: loginUserCart) {
		    Integer productTotalPrice = 0;
		    Integer productTotalIncloudeTax = 0;
	    	Products product = productsRepository.findById(cart.getProductId()).get();
	    	productTotalPrice = cart.getQuantity() * product.getPrice();
	    	productTotalIncloudeTax = cart.getQuantity() * product.getIncludeTax();
	    	cartTotalPrice = cartTotalPrice + productTotalPrice;
	    	cartTotalIncludeTax = cartTotalIncludeTax + productTotalIncloudeTax;
	    }
	    
	    model.addAttribute("cartList", cartList);
	    
	    return "user/cartDetail";
	}
	
	@RequestMapping(path = "/user/cart/delete")
	public String deleteCart(@RequestParam("cartId") Integer cartId) {
	    cartsRepository.deleteById(cartId);

	    return "redirect:/user/cartDeteil";
	}
}
