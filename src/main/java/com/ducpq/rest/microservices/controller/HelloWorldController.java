package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping("/hello-world/{name}")
	public HelloWorldBean helloWorldBeanName(@PathVariable(name = "name") String name) {
		return new HelloWorldBean("Hello " + name);
	}
	
}
