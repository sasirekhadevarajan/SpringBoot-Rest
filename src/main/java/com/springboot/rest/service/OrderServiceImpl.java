package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.Order;
import com.springboot.rest.entity.User;
import com.springboot.rest.exception.OrderNotFoundException;
import com.springboot.rest.exception.UserNotFoundException;
import com.springboot.rest.repository.OrderRepository;
import com.springboot.rest.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in our records.");
		}
		return user.get().getOrders();
	}
	
	public Order saveOrder(Long userid, Order order) throws UserNotFoundException {
		Optional<User> userTemp = userRepository.findById(userid);
		if (!userTemp.isPresent()) {
			throw new UserNotFoundException("User not found in our records.");
		}
		User user = userTemp.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	public Order getOrderById(Long userid, Long orderId) throws OrderNotFoundException {
//		Optional<User> userTemp = userRepository.findById(userid);
//		if (!userTemp.isPresent()) {
//			throw new UserNotFoundException("User not found in our records.");
//		}
//		List<Order> orders = userTemp.get().getOrders();
//		Order orderFound = null;
//		for(Order order:orders) {
//			if (order.getOrderId().equals(orderId)) { 
//				System.out.println(order.getOrderId() + " " + orderId);
//				orderFound=order; 
//				break;
//			} 
//		}
//		if (orderFound==null) {
//			throw new OrderNotFoundException("Order not found in our records.");
//		}
//		return orderFound;
		
		return orderRepository.findByUserIdAndOrderId(userid, orderId);
		
	}
}
