package jp.co.sss.cytech.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.cytech.service.CustomUserDetails;

@Controller
public class MyPageController {
	
	@RequestMapping(path = "/user/myPage")
public String showMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        
        model.addAttribute("user", customUserDetails.getUser());
        
        return "user/myPage";
	}
}
