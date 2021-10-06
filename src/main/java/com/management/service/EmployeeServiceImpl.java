package com.management.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.management.dao.EmployeeDao;
import com.management.dto.EmployeeDto;
import com.management.entity.Employee;
import com.management.entity.Manager;
import com.management.exception.CustomException;
import com.management.mapper.ManagementMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ManagementMapper mapper;

	@Autowired
	private ManagerDetailService managerSevice;

	Logger logger = LogManager.getLogger();

	@Override
	public void saveEmployee(EmployeeDto employeeDto, String mailId) {
		Employee employee = mapper.convertToEntity(employeeDto, Employee.class);
		logger.info("Saving employee info with empId: ", employeeDto.getFirstName());
		Manager manager = managerSevice.findByUserMail(mailId);
		try {
			employee.setByManager(manager);
			employeeDao.saveEmployee(employee);
		} catch (Exception e) {
			throw new CustomException("Error while saving employee data.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		logger.info("Retrieving all employees...");
		List<Employee> employees = employeeDao.getAllEmployee();
		return mapper.convertToDtos(employees, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto emp) {
		logger.info("Updating employee info with empId: ", emp.getEmpId());
		Employee employee = employeeDao.findEmployee(emp.getEmpId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setMobile(emp.getMobile());
		employee.setDob(emp.getDob());
		employee.setAddress(emp.getAddress());

		employeeDao.saveEmployee(employee);
		return emp;
	}

	@Override
	@Transactional
	public void deleteEmployee(String empId) {
		logger.info("Deleting employee info with empId: ", empId);
		employeeDao.findEmployee(empId);
		try {
			employeeDao.deleteEmployee(empId);
		} catch (Exception e) {
			throw new CustomException("Error while deleting employee with id: " + empId,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
