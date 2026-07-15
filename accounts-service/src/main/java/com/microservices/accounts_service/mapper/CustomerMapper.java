package com.microservices.accounts_service.mapper;

import com.microservices.accounts_service.dto.CustomerDto;
import com.microservices.accounts_service.entity.Customer;

public class CustomerMapper {

	public Customer dtoToEntityCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customer.getEmail());
		customer.setMobileNumber(customer.getMobileNumber());
		
		return customer;
	}
	
	public CustomerDto entityToDtoCustomer(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setEmail(customer.getEmail());
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}
}
