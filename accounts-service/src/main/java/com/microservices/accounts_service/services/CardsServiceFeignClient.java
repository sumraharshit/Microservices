package com.microservices.accounts_service.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cards-service")
public interface CardsServiceFeignClient {
	
	

}
