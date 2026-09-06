package jp.co.sss.cytech.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegisterForm {
	@NotNull
	private String userName;
	@NotBlank
	private String userNameKana;
	@NotBlank
	private String email;
	@NotBlank
	private String phone;
	@NotBlank
	private String userAddress;
	@NotBlank
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
