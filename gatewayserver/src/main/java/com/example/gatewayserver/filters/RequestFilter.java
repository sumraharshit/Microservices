package com.example.gatewayserver.filters;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Order(1)
@Component
public class RequestFilter implements GlobalFilter {

	@Autowired
	FilterUtility filterUtility;

	Logger log = LoggerFactory.getLogger(RequestFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		HttpHeaders requestHeader = exchange.getRequest().getHeaders();
		if(isCoRelationIdPresent(requestHeader)){
			log.debug("CoRelationId Found in the RequestHeader: ", filterUtility.getCoRelationId(requestHeader));
		} else{
			String generatedCoRelationId = generateCoRelationId();
			exchange = filterUtility.setCoRelationId(exchange, generatedCoRelationId);
			log.debug("CorelationId has been generated: ", generatedCoRelationId);
		}
		return chain.filter(exchange);
	}

	private boolean isCoRelationIdPresent(HttpHeaders requestHeader){

		if(filterUtility.getCoRelationId(requestHeader)!=null)
				return true;
		else
			return false;
	}

	private String generateCoRelationId(){
		return UUID.randomUUID().toString();
	}

}
