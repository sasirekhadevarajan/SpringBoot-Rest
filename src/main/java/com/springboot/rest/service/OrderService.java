package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.entity.Order;
import com.springboot.rest.exception.OrderNotFoundException;
import com.springboot.rest.exception.UserNotFoundException;

public interface OrderService {
	public List<Order> getAllOrders(Long userId) throws UserNotFoundException;
	public Order saveOrder(Long userid, Order order)  throws UserNotFoundException;
	public Order getOrderById(Long userid, Long orderId) throws OrderNotFoundException;
}
