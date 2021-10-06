package com.management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.management.entity.Manager;
import com.management.exception.CustomException;
import com.management.repository.ManagerRepo;

@Repository
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	private ManagerRepo managerRepo;

	@Override
	public Manager getManagerByEmail(String mail) {
		return managerRepo.findByUserEmail(mail);
	}

	@Override
	public void save(Manager manager) {
		try {
			managerRepo.save(manager);
		} catch (Exception e) {
			throw new CustomException("Cannot save manager.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
