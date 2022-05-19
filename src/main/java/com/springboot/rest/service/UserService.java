package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import com.springboot.rest.entity.User;

public interface UserService {
	

	public List<User> getAllUsers();
	
	public User saveUser(User user);
	
	public Optional<User> getUserById(Long id);
	
	public User updateUserById(Long id, User user);
	
	public void deleteUserById(Long id);
	
	public User getUserByUsername(String username);
}
