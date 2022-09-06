package com.airlineservice.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.airlineservice.app.services.AirlneFailedException;

@ControllerAdvice
public class CustomExceptionController {

	
	@ExceptionHandler(value=AirlneFailedException.class)
	public ResponseEntity<Object> exception(AirlneFailedException airlinefailed)
	{
		return new ResponseEntity<>(AirlneFailedException.message, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
