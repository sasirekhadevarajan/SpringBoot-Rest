package com.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.dto.UserMmDto;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.UserExistsException;
import com.springboot.rest.exception.UserNameNotFoundException;
import com.springboot.rest.exception.UserNotFoundException;
import com.springboot.rest.service.UserService;

@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {
		List<User> users= userService.getAllUsers();
		return users;
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	
	@PutMapping("/updateuser/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
		try {
			return userService.updateUserById(id, user);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUserById(@PathVariable("id") Long id){
		try {
			userService.deleteUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@GetMapping("/byname/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException{
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UserNameNotFoundException("Username not found in out records.");
		}
		return user;
	}
	
	@GetMapping("/modelmapper/{id}")
	public UserMmDto getUserByIdUsingMm(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserByIdUsingMm(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
}
