package com.ducpq.rest.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}
	
	//	@Bean
	//	public CommandLineRunner commandLineRunner(UserService userService) {
	//		return runner -> findPosts(userService);
	//	}
	//
	//	private void findPosts(UserService userService) {
	//		int id = 1;
	//		User user = userService.findUserById(id);
	//		System.out.println("Posts: " + user.getPosts());
	//	}
	
}
