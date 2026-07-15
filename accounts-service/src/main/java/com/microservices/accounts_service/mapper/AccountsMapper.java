package com.microservices.accounts_service.mapper;

import com.microservices.accounts_service.dto.AccountsDto;
import com.microservices.accounts_service.entity.Accounts;

public class AccountsMapper {

	public Accounts dtoToEntityAccountsMapper(AccountsDto accountsDto) {
		Accounts account = new Accounts();
		account.setAccountNumber(accountsDto.getAccountNumber());
		account.setAccountType(account.getAccountType());
		account.setBranchName(account.getBranchName());
		
		return account;
	}
	
	public AccountsDto entityToDtoAccountsMapper(Accounts account) {
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setBranchAddress(account.getBranchName());
		accountsDto.setAccountType(account.getAccountType());
		accountsDto.setAccountNumber(account.getAccountNumber());
		
		return accountsDto;
	}
	
}
