package com.microservices.message.functions;

import com.microservices.message.messageDto.LoanMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class LoanFunction {

    private static final Logger log = LoggerFactory.getLogger(LoanFunction.class);

    @Bean
    public Function<LoanMessageDto, LoanMessageDto> email(){
        return loanMessageDto -> {
            log.info("The email for the loan creation has been sent: " + loanMessageDto.loanNumber());
            return loanMessageDto;
        };
    }

    @Bean
    public Function<LoanMessageDto, String> sms(){
        return loanMessageDto -> {
            log.info("The sms for the loan has been sent");
            return loanMessageDto.mobileNumber();
        };
    }
}
