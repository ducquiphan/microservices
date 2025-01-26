package com.ducpq.rest.microservices.dao;

import com.ducpq.rest.microservices.entity.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDao
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@Component
public class PostDao {
	// Using static list
	private static final List<Post> users = new ArrayList<>();
	
}
