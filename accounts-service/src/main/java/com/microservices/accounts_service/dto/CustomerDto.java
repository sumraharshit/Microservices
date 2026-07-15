package com.microservices.accounts_service.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

	@NotEmpty(message = "Name cannot be null or empty")
	@Size(min=5, max = 30, message = "Name should be between 5 and 30 characters")
	private String name;
	
	@NotEmpty(message = "Email cannot be null or empty")
	@Email(message = "email message should be a valid value")
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	private Set<AccountsDto> accountsDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<AccountsDto> getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(Set<AccountsDto> accountsDto) {
		this.accountsDto = accountsDto;
	}
	
	
}
