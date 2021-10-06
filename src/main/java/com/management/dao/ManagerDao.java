package com.management.dao;

import com.management.entity.Manager;

public interface ManagerDao {

	Manager getManagerByEmail(String mail);

	void save(Manager convertToEntity);

}
