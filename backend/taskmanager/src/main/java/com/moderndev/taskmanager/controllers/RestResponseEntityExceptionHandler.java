package com.moderndev.taskmanager.controllers;

import java.time.LocalDateTime;
import java.util.*;

import com.moderndev.taskmanager.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({
			ResourceNotFoundException.class,
			ResourceAlreadyExistsException.class
	})
	public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		
		if (ex instanceof ResourceNotFoundException) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return handleResourceNotFoundException((ResourceNotFoundException) ex, headers, status, request);
		}else if(ex instanceof ResourceAlreadyExistsException){
			HttpStatus status = HttpStatus.CONFLICT;
			return handleResourceAlreadyExistsException((ResourceAlreadyExistsException) ex, headers, status, request);
		}else {
			// Unknown exception, typically a wrapper with a common MVC exception as cause
			// (since @ExceptionHandler type declarations also match first-level causes):
			// We only deal with top-level MVC exceptions here, so let's rethrow the given
			// exception for further processing through the HandlerExceptionResolver chain.
			throw ex;
		}
	}
	
	// Custom exceptions
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

		var body = buildBody("Resource not found", ex.getMessage());
		
		return new ResponseEntity<Object>(body, headers, status);
	}

	public ResponseEntity<Object>  handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

		var body = buildBody("Resource already exists", ex.getMessage());

		return new ResponseEntity<Object>(body, headers, status);
	}
	
	//Overridden exceptions
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Set<String> errors = new HashSet<>();
		ex.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError)err).getField();
			String errorMessage = err.getDefaultMessage();
			errors.add(String.format("'%s' %s", fieldName, errorMessage));
		});
		
		var body = buildBody("Validation error", errors);
		
		return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	private Map<String, Object>  buildBody(String message, Object details){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message);
		body.put("details", details);
		return body;
	}
}
