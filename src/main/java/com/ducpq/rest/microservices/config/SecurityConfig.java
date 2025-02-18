package com.ducpq.rest.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-18
 */
@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// All requests should be authenticated
		http.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET).permitAll()
						.anyRequest().authenticated())
				// If a request is not authenticated, a web page is shown
				.httpBasic(Customizer.withDefaults())// Use Basic Auth or JWT instead of sessions
				// CSRF -> POST, PUT
				.csrf(AbstractHttpConfigurer::disable) // csrf -> csrf.disable() - Disable CSRF since RESTful APIs do not need
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));// REST APIs should be stateless
		return http.build();
	}
}
