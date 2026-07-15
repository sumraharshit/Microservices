package com.microservices.loans_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.microservices.loans_service.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoanAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> loansAlreadyExistsExceptionHandler(LoanAlreadyExistsException exception, WebRequest webRequest) {
		ErrorResponseDto errorResponse = new ErrorResponseDto(
				webRequest.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()
				);
		return new ResponseEntity<ErrorResponseDto>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorResponseDto errorResponse = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ErrorResponseDto>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
