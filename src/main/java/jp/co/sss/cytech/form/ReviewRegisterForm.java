package jp.co.sss.cytech.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ReviewRegisterForm {
	@NotBlank(message = "名前を入力してください")
	private String userName;
	
	@NotNull(message = "評価を選択してください")
	private Integer rating;
	
	@Email(message = "正しいメールアドレス形式で入力してください")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	@NotBlank(message = "コメントを入力してください")
	private String comment;

	private MultipartFile reviewImgPath;
	
	private Integer productId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public MultipartFile getReviewImgPath() {
		return reviewImgPath;
	}
	public void setReviewImgPath(MultipartFile reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
