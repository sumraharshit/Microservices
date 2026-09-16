package com.microservices.message.messageDto;

public record LoanMessageDto(String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid, int outstandingAmount) {
}
