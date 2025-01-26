package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.dao.UserDao;
import com.ducpq.rest.microservices.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@RestController()
@RequestMapping("/users")
public class UserController {
	
	private final UserDao userDao;
	
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@GetMapping("")
	public List<User> retrieveAllUsers() {
		return userDao.findAll();
	}
	
	@GetMapping("/{userId}")
	public User retrieveUser(@PathVariable("userId") int userId) {
		return userDao.findById(userId);
	}
	
	
}
