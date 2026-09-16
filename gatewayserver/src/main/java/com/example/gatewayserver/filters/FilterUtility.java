package com.example.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORELATIONID = "bank-corelation-id";
    public String getCoRelationId(HttpHeaders requestHeaders){
        if(requestHeaders.get(CORELATIONID)!=null){
            List<String> listHeader = requestHeaders.get(CORELATIONID);
            return listHeader.stream().findFirst().get();
        }
        else{
            return null;
        }
    }

    public ServerWebExchange mutateRequestHeader(ServerWebExchange exchange, String name, String value){
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCoRelationId(ServerWebExchange exchange, String corelationId){
        return this.mutateRequestHeader(exchange,CORELATIONID,corelationId);
    }
}
