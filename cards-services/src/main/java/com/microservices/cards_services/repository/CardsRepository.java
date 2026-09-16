package com.microservices.cards_services.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.cards_services.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{

	Optional<Cards> findByMobileNumber(String mobileNumber);

}
