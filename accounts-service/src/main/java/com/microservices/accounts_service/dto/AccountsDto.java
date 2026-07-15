package com.microservices.accounts_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

	@NotEmpty(message = "Account Number cannot be null")
	@Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message = "Account Type cannot be null or empty")
	private String accountType;
	
	@NotEmpty(message = "Branch Address cannot be null or empty")
	private String branchAddress;
	
	
}
