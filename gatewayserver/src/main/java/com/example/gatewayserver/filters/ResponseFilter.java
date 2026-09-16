package com.example.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {

    Logger log = LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter globalFilter(FilterUtility filterUtility){
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                HttpHeaders requestHeader = exchange.getRequest().getHeaders();
                String corelationId = filterUtility.getCoRelationId(requestHeader);

                if(!exchange.getResponse().getHeaders().containsHeader(filterUtility.CORELATIONID)){
                    exchange.getResponse().getHeaders().add(FilterUtility.CORELATIONID, corelationId);
                    log.debug("Inserted the corelationId in the responseHeader: ", corelationId);
                }

            }));

        };
    }

}
