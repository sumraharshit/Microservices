package com.microservices.cards_services.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorResponseDto {

	private String apiPath;
	
	private HttpStatus statusCode;
	
	private String description;
	
	private LocalDateTime localDateTime;
}
