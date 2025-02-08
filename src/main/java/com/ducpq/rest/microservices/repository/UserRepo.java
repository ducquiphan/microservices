package com.ducpq.rest.microservices.repository;

import com.ducpq.rest.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepo
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-08
 */
public interface UserRepo extends JpaRepository<User, Integer> {
}
