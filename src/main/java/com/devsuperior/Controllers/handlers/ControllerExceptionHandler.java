package com.devsuperior.Controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.Services.exceptions.DataBaseException;
import com.devsuperior.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dto.CustomError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class) // é uma anotation para interceptar esse tipo de exceção.
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	//HttpServletRequest request = é apartir desse objeto que iremos obter a URL que foi chamada que deu exceção


	@ExceptionHandler(DataBaseException.class) // é uma anotation para interceptar esse tipo de exceção.
	public ResponseEntity<CustomError> database(DataBaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}

}