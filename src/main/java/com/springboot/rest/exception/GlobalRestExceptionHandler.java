package com.springboot.rest.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice
public class GlobalRestExceptionHandler {
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDetails handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),"Rest",request.getDescription(false),"ER002");
		return errorDetails;
	}
}
