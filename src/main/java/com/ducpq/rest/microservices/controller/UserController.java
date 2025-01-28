package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.dao.UserDao;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
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
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = userDao.findAll();
		List<EntityModel<?>> entityModels = new ArrayList<>();
		users.forEach(user -> {
			WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()));
			entityModels.add(EntityModel.of(user).add(link.withRel("self")));
		});
		return ResponseEntity.ok(entityModels);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> retrieveUser(@PathVariable("userId") int userId) {
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		EntityModel<?> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return ResponseEntity.ok(entityModel);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
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
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		userDao.deleteById(userId);
		
		return ResponseEntity.ok("Deleted user with id: " + userId);
	}
	
}
