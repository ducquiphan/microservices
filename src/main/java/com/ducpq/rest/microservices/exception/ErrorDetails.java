package com.ducpq.rest.microservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ErrorDetails
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-26
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String description;
}
