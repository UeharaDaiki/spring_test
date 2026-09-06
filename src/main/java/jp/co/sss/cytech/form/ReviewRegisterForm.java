package jp.co.sss.cytech.form;

import org.springframework.web.multipart.MultipartFile;

public class ReviewRegisterForm {

	private String userName;

	private Integer rating;

	private String email;

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
