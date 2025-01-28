package com.ducpq.rest.microservices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Name
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-28
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Name {
	private String firstName;
	private String lastName;
}
