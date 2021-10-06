package com.management.service;

import java.util.List;

import com.management.dto.EmployeeDto;

public interface EmployeeService {

	void saveEmployee(EmployeeDto employeeDto, String managerMail);

	List<EmployeeDto> getAllEmployee();

	void updateEmployee(EmployeeDto emp);

	void deleteEmployee(String empId);

}
