package jp.co.sss.cytech.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.cytech.entity.Reviews;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.ReviewRegisterForm;
import jp.co.sss.cytech.repository.ReviewsRepository;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class ReviewResisterController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	@RequestMapping(path = "/user/reviewRegister/{productId}")
	public String showReviewRegister(@PathVariable("productId") Integer productId,@ModelAttribute("reviewRegisterForm") ReviewRegisterForm form, Model model) {
		form.setProductId(productId);
	    return "user/reviewRegister";
	}
	
	@RequestMapping(path = "/user/reviewRegister",method=RequestMethod.POST)
	public String reviewRegister(@RequestParam("reviewImgPath") MultipartFile imageFile, @Validated ReviewRegisterForm form, BindingResult bindingResult, Model model) throws IllegalStateException, IOException {

		if (form.getReviewImgPath() == null || form.getReviewImgPath().isEmpty()) {
			bindingResult.rejectValue("reviewImgPath", "error.reviewImgPath", "画像ファイルを選択してください。");
			return "user/reviewRegister";
		}
		if (bindingResult.hasErrors()) {
			
			return "user/reviewRegister";
	    }
	    
		
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> loginUser= userRepository.findByEmail(email);
		
	    String originalFilename = imageFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + extension;
        String projectPath = new File("").getAbsolutePath();
        Path uploadDir = Paths.get(projectPath, "src/main/resources/static/images/");
        Path path = Paths.get(uploadDir + newFilename);
        Path filePath = uploadDir.resolve(newFilename);

        imageFile.transferTo(filePath.toAbsolutePath());
		Reviews review = new Reviews();
		review.setUserId(loginUser.get().getUserId());
		review.setProductId(form.getProductId());
		review.setRating(form.getRating());
		review.setComment(form.getComment());
		review.setDummyUserName(form.getUserName());
        review.setReviewImgPath("/images/" + newFilename);
		reviewsRepository.save(review);
		
	    return "redirect:/user/productList";
	}
}
