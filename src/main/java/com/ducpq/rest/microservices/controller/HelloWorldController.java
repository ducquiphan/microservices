package com.ducpq.rest.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
// Rest API
@RestController
public class HelloWorldController {

	// /hello-world
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
}
