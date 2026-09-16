package com.microservices.cards_services.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.cards_services.dto.CardsDto;
import com.microservices.cards_services.entity.Cards;
import com.microservices.cards_services.repository.CardsRepository;
import com.microservices.cards_services.service.CardsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService{
	
	private final CardsRepository cardsRepository;
	private final LoanFeignClient loanFeignClient;
	
//	CardsMapper cardsMapper = new CardsMapper();

	@Override
	public CardsDto getCardDetails(String mobileNumber) {
		
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new IllegalArgumentException("Number does not exists")
				);
		
//		return cardsMapper.cardsEntityToDto(cards);
		
		CardsDto cardsDto = new CardsDto();
		
		
		cardsDto.setAmountUsed(cards.getAmountUsed());
		cardsDto.setAvailableAmount(cards.getAvailableAmount());
		cardsDto.setCardNumber(cards.getCardNumber());
		cardsDto.setCardType(cards.getCardType());
		cardsDto.setMobileNumber(cards.getMobileNumber());
		cardsDto.setTotalLimit(cards.getTotalLimit());
		
		cardsDto.setLoansDto(loanFeignClient.fetchLoanDetails(mobileNumber).getBody());
		
		return cardsDto;
		 
		
	}
	
	@Override
    public void createCard(Cards card) {
		cardsRepository.save(card);
    }
	
	

}
