package com.microservices.accounts_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseDto {

	private String statusCode;
	
	private String statusMessage;
	
//	public ResponseDto(String statusCode, String statusMessage){
//		this.statusCode = statusCode;
//		this.statusMessage = statusMessage;
//	}
}
