package com.ducpq.rest.microservices.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HelloWorldBean
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@Getter
@Setter
@ToString
public class HelloWorldBean {
	
	private String message;
	
	public HelloWorldBean(String message) {
		this.message = message;
	}
	
}
