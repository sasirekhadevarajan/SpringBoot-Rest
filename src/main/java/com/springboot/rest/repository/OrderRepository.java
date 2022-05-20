package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long>{
	public Order findByUserIdAndOrderId(Long userId, Long orderId);
}