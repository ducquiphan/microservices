package com.ducpq.rest.microservices.repository;

import com.ducpq.rest.microservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepo
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-08
 */
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findPostsByUserId(int userId);
}
