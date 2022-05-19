package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
	User findUserByUsername(String username);
}
