package com.management.dto;

import javax.validation.constraints.NotBlank;

public class AuthRequest {

	@NotBlank(message = "username required")
	private String userName;

	@NotBlank(message = "username required")
	private String password;

	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
