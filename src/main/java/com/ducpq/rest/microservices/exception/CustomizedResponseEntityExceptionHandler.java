package com.ducpq.rest.microservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * CustomizedResponseEntityExceptionHandler
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-26
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<?> handleUserNotFoundExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		//		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
		//				request.getDescription(false));
		//		String errorMessage = String.format("Total errors: %d\n", ex.getErrorCount());
		//		errorMessage += ex.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
		
		// Have to use StringBuilder because it is mutable (can be modified)
		StringBuilder errorMessage = new StringBuilder();
		ex.getAllErrors().stream().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"Total Errors:" + ex.getErrorCount() + " Error(s):" + errorMessage,
				request.getDescription(false));
		
		return handleExceptionInternal(ex, errorDetails, headers, status, request);
	}
}
