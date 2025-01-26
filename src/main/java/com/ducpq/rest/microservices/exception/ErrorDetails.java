package com.ducpq.rest.microservices.exception;

import java.time.LocalDateTime;

/**
 * ErrorDetails
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-26
 */
public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String description;
	
	public ErrorDetails(LocalDateTime timestamp, String message, String description) {
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
