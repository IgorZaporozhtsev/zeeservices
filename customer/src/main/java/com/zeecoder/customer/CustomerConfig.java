package com.zeecoder.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced //means that the request goes one of multiple instance of Fraud service
    // (http://FRAUD/api/v1/fraud-check/{customerId}")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
