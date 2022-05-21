package com.springboot.rest.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.UserNotFoundException;
import com.springboot.rest.service.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") Long userid) {
		
		try {
		Optional<User> userTemp = userService.getUserById(userid);
		User user = userTemp.get();
		
		Set<String> fields = new HashSet<String>();
		fields.add("username");
		fields.add("firstname");
		fields.add("email");
		fields.add("orders");
		
		FilterProvider fp = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(fp);
		
		return mapper;
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}

	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserByIdUsingParam(@PathVariable("id") Long userid, @RequestParam Set<String> fields) {
		
		try {
		Optional<User> userTemp = userService.getUserById(userid);
		User user = userTemp.get();
				
		FilterProvider fp = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(fp);
		
		return mapper;
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}

}
