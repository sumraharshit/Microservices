package com.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity  //bcz the gateway is a part of the spring reactive framework
public class SecurityConfig {

    @Bean
   public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){

        serverHttpSecurity.authorizeExchange(
                exchangeCustomizer -> exchangeCustomizer.pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("/bank/accounts-service/**").hasRole("ACCOUNT")
                        .pathMatchers("/bank/cards-services/**").hasRole("CARD")
                        .pathMatchers("/bank/loans-service/**").hasRole("LOAN"))
                .oauth2ResourceServer(oauth->oauth.jwt(
                        jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthorityConverter())
                ));

        serverHttpSecurity.csrf(csrf->csrf.disable());

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthorityConverter() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleCovertor());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
