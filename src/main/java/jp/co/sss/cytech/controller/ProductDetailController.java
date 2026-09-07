package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.cytech.entity.Companies;
import jp.co.sss.cytech.entity.Products;
import jp.co.sss.cytech.entity.Reviews;
import jp.co.sss.cytech.repository.CompaniesRepository;
import jp.co.sss.cytech.repository.ProductsRepository;
import jp.co.sss.cytech.repository.ReviewsRepository;

@Controller
public class ProductDetailController {
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	CompaniesRepository companiesRepository;
	@Autowired
	ReviewsRepository reviewsRepository;
	
	@RequestMapping(path = "/user/productDetail/{productId}")
	public String showProductDetail(@PathVariable Integer productId, Model model) {
		
		Products product = productsRepository.findById(productId).get();
		Companies company = companiesRepository.findById(product.getCompanyId()).get();
		List<Reviews> reviewList = reviewsRepository.findTop1ByProductIdOrderByCreatedAtDesc(productId);
		
		Reviews review = null;
		if (reviewList != null && !reviewList.isEmpty()) {
			review = reviewList.get(0);
		}
		
		model.addAttribute("product",product);
		model.addAttribute("company",company);
		model.addAttribute("review",review);
	return "user/productDetail";
	}
}
