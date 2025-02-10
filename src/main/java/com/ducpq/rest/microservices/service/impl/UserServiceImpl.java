package com.ducpq.rest.microservices.service.impl;

import com.ducpq.rest.microservices.entity.Post;
import com.ducpq.rest.microservices.entity.User;
import com.ducpq.rest.microservices.repository.PostRepo;
import com.ducpq.rest.microservices.repository.UserRepo;
import com.ducpq.rest.microservices.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;
	private final PostRepo postRepo;
	
	/**
	 * @return
	 */
	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}
	
	/**
	 * @param userId
	 * @return
	 */
	@Override
	public User findUserById(int userId) {
		return userRepo.findById(userId).orElse(null);
	}
	
	/**
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	/**
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public User updateUser(User user) {
		if (userRepo.existsById(user.getId())) {
			return userRepo.save(user);
		} else {
			return null;
		}
	}
	
	/**
	 * @param userId
	 */
	@Override
	@Transactional
	public void deleteUserById(int userId) {
		userRepo.deleteById(userId);
	}
	
	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Post> findAllPostsByUserId(int userId) {
		return postRepo.findPostsByUserId(userId);
	}
	
	/**
	 * @param post
	 * @return
	 */
	@Override
	@Transactional
	public Post createPost(Post post) {
		return postRepo.save(post);
	}
}
