package com.microservices.accounts_service.services.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.accounts_service.constants.AccountConstants;
import com.microservices.accounts_service.dto.CustomerDto;
import com.microservices.accounts_service.entity.Accounts;
import com.microservices.accounts_service.entity.Customer;
import com.microservices.accounts_service.exceptions.CustomerAlreadyExistsException;
import com.microservices.accounts_service.exceptions.ResourceNotFoundException;
import com.microservices.accounts_service.mapper.AccountsMapper;
import com.microservices.accounts_service.mapper.CustomerMapper;
import com.microservices.accounts_service.repository.AccountsRepository;
import com.microservices.accounts_service.repository.CustomerRepository;
import com.microservices.accounts_service.services.AccountsService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
public class AccountServiceImpl implements AccountsService{
	
	@Autowired
	private final AccountsRepository accountsRepository;
	
	@Autowired
	private final CustomerRepository customerRepository;
	
	CustomerMapper customerMapper = new CustomerMapper();
	AccountsMapper accountMapper = new AccountsMapper();
	
	public AccountServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
		this.accountsRepository = accountsRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		
		Optional<Customer> checkCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(checkCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer Already Exists with the number " + customerDto.getMobileNumber());
		}
		
		
		
		Customer customer = customerRepository.save(customerMapper.dtoToEntityCustomer(customerDto));
		accountsRepository.save(createNewAccount(customer));
		
	}

	
	public Accounts createNewAccount(Customer customer) {
		
		Accounts account = new Accounts();
		
//		accounts.setCustomerId(customer.getCustomerId());
		account.setAccountType(AccountConstants.SAVINGS);
		account.setBranchName(AccountConstants.ADDRESS);
		account.setAccountNumber(new Random().nextLong(9999));
		account.setCustomer(customer);
		customer.getAccountsSet().add(account);
		
		return account;
	}
	
	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer not found with the mobile number " + mobileNumber)
				);
				
		Set<Accounts> accountsList = accountsRepository.findByCustomer(customer.getCustomerId()).orElseThrow();
		CustomerDto customerDto = customerMapper.entityToDtoCustomer(customer);
		customerDto.setAccountsDto(accountsList.stream().map(e -> accountMapper.entityToDtoAccountsMapper(e)).collect(Collectors.toSet()));
		
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		
		return false;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found with the mobile number" + mobileNumber)
        );
        accountsRepository.deleteByCustomer(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
	}

	
}
