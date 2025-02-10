package com.ducpq.rest.microservices.repository;

import com.ducpq.rest.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("from User u join fetch u.posts where u.id = :id")
	List<User> findUserWithPostsById(int id);
}
