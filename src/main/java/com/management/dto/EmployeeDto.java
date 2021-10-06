package com.management.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EmployeeDto {

	@NotBlank(message = "Employee ID cannot be blank")
	private String empId;

	@NotBlank(message = "Last name cannot be null")
	private String firstName;

	@NotBlank(message = "Last name cannot be null")
	private String lastName;

	private String address;

	@Min(value = 10, message = "Enter 10 digit number")
	private String mobile;

	private Date dob;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
