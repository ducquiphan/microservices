package com.ducpq.rest.microservices.entity;

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
	private String name;
	private LocalDate birthDate;
}
