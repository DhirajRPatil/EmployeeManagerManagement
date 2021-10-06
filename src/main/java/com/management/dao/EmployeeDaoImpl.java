package com.management.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.management.entity.Employee;
import com.management.exception.CustomException;
import com.management.repository.EmployeeRepo;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employees = employeeRepo.findAll();
		if (!employees.isEmpty()) {
			return employees;
		}
		throw new CustomException("No employee found.", HttpStatus.NOT_FOUND);
	}

	@Override
	public Employee findEmployee(String employeeId) {
		Employee employee = employeeRepo.findByEmpId(employeeId);
		if (employee != null) {
			return employee;
		}
		throw new CustomException("Employee not found with id: " + employeeId, HttpStatus.NOT_FOUND);
	}

	@Override
	public void deleteEmployee(String empId) {
		employeeRepo.deleteByEmpId(empId);

	}
}
