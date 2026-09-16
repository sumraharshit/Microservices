package com.microservices.loans_service.dto;

public record LoanMessageDto(String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid, int outstandingAmount) {
}
