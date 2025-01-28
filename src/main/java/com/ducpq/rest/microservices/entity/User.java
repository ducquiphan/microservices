package com.ducpq.rest.microservices.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Users
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
	private Integer id;
	@Size(min = 2, message = "Name must be at least 2 characters long")
	private String name;
	@Past(message = "Birth date must be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
}
