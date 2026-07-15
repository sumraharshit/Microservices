package com.microservices.accounts_service.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}

}
