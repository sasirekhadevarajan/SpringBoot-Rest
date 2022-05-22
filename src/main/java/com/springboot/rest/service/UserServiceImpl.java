package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.dto.UserMmDto;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.UserExistsException;
import com.springboot.rest.exception.UserNotFoundException;
import com.springboot.rest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User saveUser(User user) throws UserExistsException {
		User userTemp = userRepository.findUserByUsername(user.getUsername());
		if (userTemp != null) {
			throw new UserExistsException("Username already exists in our records.");
		}
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in our records.");
		}
		return user;
	}
	
	public UserMmDto getUserByIdUsingMm(Long id) throws UserNotFoundException {
		Optional<User> userTemp = userRepository.findById(id);
		if (!userTemp.isPresent()) {
			throw new UserNotFoundException("User not found in our records.");
		}
		User user = userTemp.get();
		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
		return userMmDto;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> userTemp = userRepository.findById(id);
		if (userTemp.isPresent()) {
			user.setId(id);
			return userRepository.save(user);
		} else {
			throw new  UserNotFoundException("User not found in our records.");
		}
	}
	
	public void deleteUserById(Long id)  throws UserNotFoundException {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new  UserNotFoundException("User not found in our records.");
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
}
