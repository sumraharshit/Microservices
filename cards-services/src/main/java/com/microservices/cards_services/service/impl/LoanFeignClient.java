package com.microservices.cards_services.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.microservices.cards_services.dto.LoansDto;


import jakarta.validation.constraints.Pattern;

@FeignClient(name="loans-service", fallback = LoansFallBack.class)
public interface LoanFeignClient {
	
	@GetMapping("/api/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber);

}
