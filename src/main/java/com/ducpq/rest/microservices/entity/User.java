package com.ducpq.rest.microservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Users
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@Getter
@Setter
@ToString(exclude = "posts")
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Size(min = 2, message = "Name must be at least 2 characters long")
	private String name;
	@Past(message = "Birth date must be in the past")
	private LocalDate birthDate;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	public User(Integer id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
}