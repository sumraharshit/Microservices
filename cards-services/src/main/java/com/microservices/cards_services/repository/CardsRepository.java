package com.microservices.cards_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.cards_services.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{

}
