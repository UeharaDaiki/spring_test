package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sss.cytech.entity.Categories;
import jp.co.sss.cytech.repository.CategoriesRepository;
import jp.co.sss.cytech.service.CustomUserDetails;

@ControllerAdvice // 💖 全ての画面描画前に自動実行される
public class GlobalHeaderAdvice {
	@Autowired
    private CategoriesRepository categoriesRepository;

    // 💖 全てのHTMLで ${loginUserName} が使えるようになる
    @ModelAttribute("loginUserName")
    public String getLoginUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        Object principal = auth.getPrincipal();

        // CustomUserDetails から安全に userName を取り出す
        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUser().getUserName();
        }

        return null;
    }
    
    @ModelAttribute("categoryList")
    public List<Categories> getCategoryList() {
        return categoriesRepository.findAll();
    }
}