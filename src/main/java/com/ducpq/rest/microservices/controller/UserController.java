package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.dao.UserDao;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		return user;
	}
	
	@PostMapping("")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User createdUser = userDao.save(user);
		// users/4 => users/{id}, user.getId()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId())
				.toUri();
		// location - /user/{userId}
		return ResponseEntity.created(location).body(createdUser);
	}
}
