package com.microservices.accounts_service.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.microservices.accounts_service.entity.Accounts;

import jakarta.transaction.Transactional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{

	Optional<Set<Accounts>> findByCustomer(Long customerId);
	
	
	@Transactional
	@Modifying
	void deleteByCustomer(Long customerId);
}
