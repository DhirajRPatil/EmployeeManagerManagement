package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

	Manager findByUserEmail(String mail);

}
