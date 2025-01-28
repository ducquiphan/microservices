package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.entity.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * HelloWorldController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-20
 */
@RestController()
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/hello-world/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}
	
	
	@GetMapping("/hello-world/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello: %s", name));
	}
	
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("greeting.morning.message", null, "Default message!", locale);
		//return "Hello World Internationalized!";
		
	}
	
}
