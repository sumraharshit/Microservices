package com.microservices.cards_services.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.cards_services.dto.CardsDto;
import com.microservices.cards_services.entity.Cards;
import com.microservices.cards_services.service.CardsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardsController {

	private static final Logger log = LoggerFactory.getLogger(CardsController.class);

	private final CardsService cardsService;
	

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/cardDetails")
	public ResponseEntity<CardsDto> cardDetails(@RequestParam String mobileNumber){

		log.info("The request to fetch cards details is triggered");
		return new ResponseEntity<CardsDto>(cardsService.getCardDetails(mobileNumber), HttpStatus.OK);
	}
	
	@PostMapping("/createCard")
	public ResponseEntity<String> createCard(@RequestBody Cards card){
		cardsService.createCard(card);
		return new ResponseEntity<String>("Card created", HttpStatus.OK);
	}
	
}
