package com.management.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.management.dto.ManagerDto;
import com.management.entity.Manager;

public interface ManagerDetailService extends UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public void save(ManagerDto managerDto);

	public Manager findByUserMail(String email);
}
