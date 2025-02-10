package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.dao.UserDao;
import com.ducpq.rest.microservices.entity.Post;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.exception.UserNotFoundException;
import com.ducpq.rest.microservices.repository.PostRepo;
import com.ducpq.rest.microservices.repository.UserRepo;
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
@RequestMapping("/v1/jpa/users")
public class UserJpaController {
	
	private final UserDao userDao;
	
	private final UserRepo userRepo;
	private final PostRepo postRepo;
	
	public UserJpaController(UserDao userDao, UserRepo userRepo, PostRepo postRepo) {
		this.userDao = userDao;
		this.userRepo = userRepo;
		this.postRepo = postRepo;
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
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Id: " + userId));
		
		EntityModel<?> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return ResponseEntity.ok(entityModel);
	}
	
	@PostMapping("")
	@Transactional
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
	@Transactional
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Id: " + userId));
		
		userRepo.deleteById(userId);
		
		return ResponseEntity.ok("Deleted user with id: " + userId);
	}
	
	@GetMapping("/{userId}/posts")
	public ResponseEntity<?> retrievePostsForUser(@PathVariable("userId") int userId) {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("Id: " + userId);
		}
		
		List<Post> userPosts = user.getPosts();
		
		return ResponseEntity.ok(userPosts);
	}
	
	@PostMapping("/{userId}/posts")
	@Transactional
	public ResponseEntity<?> createPostForUser(@PathVariable("userId") int userId, @Valid @RequestBody Post post) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Id: " + userId));
		post.setUser(user);
		
		Post createdPost = postRepo.save(post);
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
		
		userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Id: " + userId));
		Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id: " + postId + " not found"));
		EntityModel<?> entityModel = EntityModel.of(post);
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrievePostsForUser(userId));
		entityModel.add(link.withRel("user-posts"));
		return ResponseEntity.ok(entityModel);
	}
}
