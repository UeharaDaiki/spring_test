package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.UserRegisterForm;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/user/register")
	public String showRegister(UserRegisterForm userRegisterForm) {
		return "user/register";
	}
	
	@RequestMapping(path="user/register", method = RequestMethod.POST)
	public String regster(@Validated UserRegisterForm userRegisterForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			
			return "user/register";
	    }
		User user = new User();
		user.setUserName(userRegisterForm.getUserName());
		user.setUserNameKana(userRegisterForm.getUserNameKana());
		user.setEmail(userRegisterForm.getEmail());
		user.setPasswords(userRegisterForm.getPasswords());
	    user.setPhone(userRegisterForm.getPhone());
	    user.setUserAddress(userRegisterForm.getUserAddress());
	    
	    userRepository.save(user);
		return "redirect:/user/register";
	}
	
	@RequestMapping(path = "/user/login")
	public String showLogin() {
		return "user/login";
	}
	

}
