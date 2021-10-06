package com.management.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.dao.ManagerDao;
import com.management.dto.ManagerDto;
import com.management.entity.Manager;
import com.management.exception.CustomException;
import com.management.mapper.ManagementMapper;

@Service
public class ManagerDetailsServiceImpl implements ManagerDetailService {

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private ManagementMapper mapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager manager = managerDao.getManagerByEmail(username);
		if (manager != null) {
			return new User(manager.getUserEmail(), manager.getPassword(), new ArrayList<>());
		}
		throw new CustomException("User is not authorized", HttpStatus.UNAUTHORIZED);
	}

	@Override
	public void save(ManagerDto managerDto) {
		Manager manager = mapper.convertToEntity(managerDto, Manager.class);
		manager.setPassword(bCryptPasswordEncoder.encode(managerDto.getPassword()));
		managerDao.save(manager);
	}

	@Override
	public Manager findByUserMail(String email) {
		Manager manager = managerDao.getManagerByEmail(email);
		if (manager != null) {
			return manager;
		}
		throw new CustomException("User not found with mailId: " + email, HttpStatus.NOT_FOUND);
	}

}
