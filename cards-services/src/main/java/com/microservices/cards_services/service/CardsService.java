package com.microservices.cards_services.service;


import com.microservices.cards_services.dto.CardsDto;
import com.microservices.cards_services.entity.Cards;

public interface CardsService {

	public CardsDto getCardDetails(String mobileNumber);
	
	public void createCard(Cards card);

}
