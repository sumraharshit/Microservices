package com.microservices.accounts_service.controller;



import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.accounts_service.constants.AccountConstants;
import com.microservices.accounts_service.dto.CustomerDto;
import com.microservices.accounts_service.dto.ResponseDto;
import com.microservices.accounts_service.services.AccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

	@Autowired
	private final AccountsService accountsService;
	
	public AccountsController(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	private static final Logger log = LoggerFactory.getLogger(AccountsController.class);

	@Retry(name="helloWorld", fallbackMethod = "helloWorldFallBack")
	@GetMapping("/")
	public String helloWorld() throws TimeoutException{

		log.debug("the hello world is invoked");

		throw new TimeoutException();
	}

	public String helloWorldFallBack(Throwable throwable) {

		log.debug("the fallback method for the helloworld is invoked");
		return "Hello Fall Back";
	}

	@RateLimiter(name="HelloComputer", fallbackMethod = "HelloComputerFallbackMethod")
	@GetMapping("/hello")
	public String HelloComputer(){
		return "Hello Computer";
	}

	public String HelloComputerFallbackMethod(Throwable throwable){
		return "Bye bye Computer";
	}



	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
		accountsService.createAccount(customerDto);
		return new ResponseEntity<ResponseDto>(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201), HttpStatus.OK);
	}
	
	 
	   
	    @GetMapping("/fetch")
	    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
	                                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                               String mobileNumber) {
	        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
	        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	    }

	  
	    @PutMapping("/update")
	    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
	        boolean isUpdated = accountsService.updateAccount(customerDto);
	        if(isUpdated) {
	        	return new ResponseEntity<ResponseDto>(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200), HttpStatus.OK);
	        }else{
	        	return new ResponseEntity<ResponseDto>(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE), HttpStatus.OK);
	        }
	    }

	    @DeleteMapping("/delete")
	    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
	        if(isDeleted) {
	        	return new ResponseEntity<ResponseDto>(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200), HttpStatus.OK);
	        }else{
	        	return new ResponseEntity<ResponseDto>(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE), HttpStatus.OK);
	        }
	    }
	
}
