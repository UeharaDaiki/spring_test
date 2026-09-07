package jp.co.sss.cytech.form;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterForm {
	@NotBlank(message = "ユーザ名を入力してください")
	private String userName;
	@NotBlank(message = "ユーザ名（カナ）を入力してください")
	private String userNameKana;
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	@NotBlank(message = "電話番号を入力してください")
	private String phone;
	@NotBlank(message = "住所を入力してください")
	private String userAddress;
	@NotBlank(message = "パスワードを入力してください")
	private String passwords;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNameKana() {
		return userNameKana;
	}
	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	


}
