package com.mayab.quality.loginunittest.dao;

import java.util.List;

import com.mayab.quality.loginunittest.model.User;

public interface IDAOLogin {
	
	User findUserByEmail(String email);
	
	int save(User u);
	
	List<User> findAll();
	
	User findById(int id);
	
	User findById(id);
	
	boolean deleteById(id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	User findByUserName(String name);

	int save(User user);

	User findUserByEmail(String email);

	List<User> findAll();

	User findById(int id);

	boolean deleteById(int id);

	User updateUser(User userOld);
	

}
