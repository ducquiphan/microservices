package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.dao.UserDao;
import com.ducpq.rest.microservices.entity.Post;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.exception.UserNotFoundException;
import com.ducpq.rest.microservices.repository.UserRepo;
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
@RequestMapping("/jpa/users")
public class UserJpaController {
	
	private final UserDao userDao;
	
	private final UserRepo userRepo;
	
	public UserJpaController(UserDao userDao, UserRepo userRepo) {
		this.userDao = userDao;
		this.userRepo = userRepo;
	}
	
	@GetMapping("")
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = userRepo.findAll();
		List<EntityModel<?>> entityModels = new ArrayList<>();
		users.forEach(user -> {
			WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()));
			entityModels.add(EntityModel.of(user).add(link.withRel("self")));
		});
		return ResponseEntity.ok(entityModels);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> retrieveUser(@PathVariable("userId") int userId) {
		User user = userRepo.findById(userId).orElse(null);
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
		User createdUser = userRepo.save(user);
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
		User user = userRepo.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		userRepo.deleteById(userId);
		
		return ResponseEntity.ok("Deleted user with id: " + userId);
	}
	
	@GetMapping("/{userId}/posts")
	public ResponseEntity<?> retrivePostsForUser(@PathVariable("userId") int userId) {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		List<Post> userPosts = user.getPosts();
		
		return ResponseEntity.ok(userPosts);
	}
	
}
