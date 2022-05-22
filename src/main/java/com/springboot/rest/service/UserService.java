package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import com.springboot.rest.dto.UserMmDto;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.UserExistsException;
import com.springboot.rest.exception.UserNotFoundException;

public interface UserService {
	

	public List<User> getAllUsers();
	
	public User saveUser(User user) throws UserExistsException;
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException;
	
	public UserMmDto getUserByIdUsingMm(Long id) throws UserNotFoundException;
	
	public User updateUserById(Long id, User user) throws UserNotFoundException;
	
	public void deleteUserById(Long id)  throws UserNotFoundException ;
	
	public User getUserByUsername(String username);
}
