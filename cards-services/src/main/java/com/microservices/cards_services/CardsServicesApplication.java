package com.microservices.cards_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsServicesApplication.class, args);
	}

}
