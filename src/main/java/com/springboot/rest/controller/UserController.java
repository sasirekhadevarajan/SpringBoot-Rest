package com.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.User;
import com.springboot.rest.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> users= userService.getAllUsers();
		return users;
	}
	
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	
	
	@PutMapping("/updateuser/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
		return userService.updateUserById(id, user);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUserById(@PathVariable("id") Long id){
		userService.deleteUserById(id);
	}
	
	@GetMapping("/users/byname/{username}")
	public User getUserByUsername(@PathVariable("username") String username){
		return userService.getUserByUsername(username);
	}
}
