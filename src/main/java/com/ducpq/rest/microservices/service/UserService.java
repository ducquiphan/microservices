package com.ducpq.rest.microservices.service;

import com.ducpq.rest.microservices.entity.Post;
import com.ducpq.rest.microservices.entity.User;

import java.util.List;

/**
 * UserService
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-10
 */
public interface UserService {
	List<User> findAllUsers();
	
	User findUserById(int userId);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	void deleteUserById(int userId);
	
	List<Post> findAllPostsByUserId(int userId);
	
	Post createPost(Post post);
	
	Post findPostById(int postId);
}
