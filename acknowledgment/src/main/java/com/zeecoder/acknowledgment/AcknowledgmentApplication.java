package com.zeecoder.acknowledgment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AcknowledgmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcknowledgmentApplication.class, args);
    }
}
