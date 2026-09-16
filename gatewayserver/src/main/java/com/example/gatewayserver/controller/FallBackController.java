package com.example.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/contact-support")
    public Mono<String> fallbackContactSupport(){
        return Mono.just("An error occured. Please try after sometime or contact support");
    }
}
