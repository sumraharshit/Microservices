package com.microservices.loans_service.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

	private String apiPath;
	
	private HttpStatus errorCode;
	
	private String errorMsg;
	
	private LocalDateTime errorTime;
}
