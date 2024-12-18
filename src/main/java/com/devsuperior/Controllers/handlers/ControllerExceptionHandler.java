package com.devsuperior.Controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.Services.exceptions.DataBaseException;
import com.devsuperior.Services.exceptions.ForbiddenException;
import com.devsuperior.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dto.CustomError;

import com.devsuperior.dto.ValidationError;
import javax.servlet.http.HttpServletRequest;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // é uma anotation para interceptar esse tipo de exceção.
	public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(Instant.now(),status.value(),"Campo requerido",request.getRequestURI());
		
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(),f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(ForbiddenException.class) // é uma anotation para interceptar esse tipo de exceção.
	public ResponseEntity<CustomError> forbidden(ForbiddenException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN; // Gera o código 403 (Forbidden)
		CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI()); 
		return ResponseEntity.status(status).body(err); // Retorna a resposta com o erro 403
		
	}

}