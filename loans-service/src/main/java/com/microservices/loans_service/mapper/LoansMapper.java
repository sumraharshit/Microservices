package com.microservices.loans_service.mapper;

import com.microservices.loans_service.dto.LoansDto;
import com.microservices.loans_service.entity.Loans;

public 	final class LoansMapper {

	public static final Loans mapToLoans(LoansDto loansDto, Loans loans) {
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setOutstandingAmount(loansDto.getOutstandingAmount());
		loans.setTotalLoan(loansDto.getTotalLoan());
		
		return loans;
}
	
	public static final LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
		
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());
		loansDto.setTotalLoan(loans.getTotalLoan());
		return loansDto;
	}
}
