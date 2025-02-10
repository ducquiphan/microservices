package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.entity.Post;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.exception.UserNotFoundException;
import com.ducpq.rest.microservices.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
@RestController
@RequestMapping("/v2/jpa/users")
public class UserJpaControllerV2 {
	
	private final UserService userService;
	
	public UserJpaControllerV2(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("")
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = userService.findAllUsers();
		List<EntityModel<?>> entityModels = new ArrayList<>();
		users.forEach(user -> {
			WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()));
			entityModels.add(EntityModel.of(user).add(link.withRel("self")));
		});
		return ResponseEntity.ok(entityModels);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> retrieveUser(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
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
		User createdUser = userService.createUser(user);
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
		User user = userService.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		userService.deleteUserById(userId);
		
		return ResponseEntity.ok("Deleted user with id: " + userId);
	}
	
	
	@GetMapping("/{userId}/posts")
	public ResponseEntity<?> retrievePostsForUser(@PathVariable("userId") int userId) {
		List<Post> posts = userService.findAllPostsByUserId(userId);
		if (posts == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping("/{userId}/posts")
	public ResponseEntity<?> createPostForUser(@PathVariable("userId") int userId, @Valid @RequestBody Post post) {
		
		User user = userService.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		post.setUser(user);
		
		Post createdPost = userService.createPost(post);
		// users/4 => users/{id}, user.getId()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdPost.getId())
				.toUri();
		// location - /user/{userId}
		return ResponseEntity.created(location).body(createdPost);
	}
	
	@GetMapping("/{userId}/posts/{postId}")
	@Transactional
	public ResponseEntity<?> getPost(@PathVariable("userId") int userId, @PathVariable("postId") int postId) {
		
		User user = userService.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		Post post = userService.findPostById(postId);
		if (post == null) {
			throw new EntityNotFoundException("Post with id: " + postId + " not found");
		}
		EntityModel<?> entityModel = EntityModel.of(post);
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrievePostsForUser(userId));
		entityModel.add(link.withRel("user-posts"));
		return ResponseEntity.ok(entityModel);
	}
	
}
