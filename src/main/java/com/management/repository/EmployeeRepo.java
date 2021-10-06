package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByEmpId(String employeeId);

	void deleteByEmpId(String empId);

}
