package com.microservices.loans_service.exception;

public class LoanAlreadyExistsException extends RuntimeException{

	public LoanAlreadyExistsException(String msg) {
		super(msg);
	}
}
