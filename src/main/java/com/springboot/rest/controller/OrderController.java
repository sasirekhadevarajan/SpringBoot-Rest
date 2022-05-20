package com.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.Order;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.OrderNotFoundException;
import com.springboot.rest.exception.UserNotFoundException;
import com.springboot.rest.service.OrderService;
import com.springboot.rest.service.UserService;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long id) throws UserNotFoundException {
		return orderService.getAllOrders(id);
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderById(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid) throws OrderNotFoundException {
		Order order = orderService.getOrderById(userid,orderid);
		if (order==null) {
			throw new OrderNotFoundException("Order not found in our records.");
		}
		return order;
	}
	
	@PostMapping("/{userid}/createOrder")
	public Order createOrder(@PathVariable("userid") Long userid, @RequestBody Order order) throws UserNotFoundException {
		
		return orderService.saveOrder(userid, order);
	}
}
