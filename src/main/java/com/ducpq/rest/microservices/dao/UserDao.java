package com.ducpq.rest.microservices.dao;

import com.ducpq.rest.microservices.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * UserDao
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@Component
public class UserDao {
	// Using a static list
	private static final List<User> users = new ArrayList<>();
	private static Integer usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "DucPhan", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Kevin", LocalDate.now().minusYears(18)));
		users.add(new User(++usersCount, "Sky", LocalDate.now().minusYears(25)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
