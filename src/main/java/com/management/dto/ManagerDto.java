package com.management.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ManagerDto {

	@NotBlank(message = "First name cannot be null")
	private String firstName;

	@NotBlank(message = "Last name cannot be null")
	private String lastName;

	@NotBlank(message = "Email cannot be null")
	private String userEmail;

	private String address;

	@NotBlank(message = "Password cannot be null")
	private String password;

	private Date dob;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
