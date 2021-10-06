package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.constant.UrlMapping;
import com.management.dto.EmployeeDto;
import com.management.http.response.CustomHttpResponse;
import com.management.http.response.ResponseMaker;
import com.management.security.config.RequestParser;
import com.management.service.EmployeeService;

/*
 * @author Dhiraj R Patil
 *
 *  ManagerController is added for providing API for save, update, get, delete Employee.
 *  
 *  I haven't introduced Role based security here because we have only User as Manager.
 */

@RestController
@RequestMapping(UrlMapping.BASE_URL)
@SuppressWarnings("rawtypes")
public class ManagerController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RequestParser requestParser;

	@PostMapping(UrlMapping.EMPLOYEE)
	public ResponseEntity<CustomHttpResponse> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		String managerMail = requestParser.getmanagerMailId();
		employeeService.saveEmployee(employeeDto, managerMail);
		return new ResponseMaker().successResponse("Employee saved successfully.", HttpStatus.OK);
	}

	@PutMapping(UrlMapping.EMPLOYEE)
	public ResponseEntity<CustomHttpResponse<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = employeeService.updateEmployee(employeeDto);
		return new ResponseMaker().successResponse(employee, "Employee updated successfully.", HttpStatus.OK);
	}

	@GetMapping(UrlMapping.EMPLOYEE)
	public ResponseEntity<CustomHttpResponse<List<EmployeeDto>>> getAllEmployee() {
		List<EmployeeDto> employees = employeeService.getAllEmployee();
		return new ResponseMaker().successResponse(employees, "User created successfully", HttpStatus.OK);
	}

	@DeleteMapping(UrlMapping.EMPLOYEE)
	public ResponseEntity<CustomHttpResponse> deleteEmployee(@RequestParam(required = true) String empId) {
		employeeService.deleteEmployee(empId);
		return new ResponseMaker().successResponse("Employee deleted successfully", HttpStatus.OK);
	}
}
