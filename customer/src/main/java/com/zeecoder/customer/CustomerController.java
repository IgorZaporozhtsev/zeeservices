package com.zeecoder.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request){
        log.info("new Customer registration{}", request);
        service.registerCustomer(request);
    }
}
