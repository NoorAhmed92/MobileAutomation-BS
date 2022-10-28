package com.kinship.automation.testdata;

import io.qameta.allure.Step;

public class LoginEntity {

	private String email;
	private String password;
	
	@Step("Get Email")
	public String getEmail() {
		return email;
	}
	
	@Step("Set Email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Step("Get Password")
	public String getPassword() {
		return password;
	}
	
	@Step("Set Password")
	public void setPassword(String password) {
		this.password = password;
	}

}