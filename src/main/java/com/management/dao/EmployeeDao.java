package com.management.dao;

import java.util.List;

import com.management.entity.Employee;

public interface EmployeeDao {

	void saveEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Employee findEmployee(String employeeId);

	void deleteEmployee(String empId);

}
