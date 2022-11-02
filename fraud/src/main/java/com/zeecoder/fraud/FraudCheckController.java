package com.zeecoder.fraud;

import com.zeecoder.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {

    private final FraudCheckService service;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID){
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerID);
        log.info("fraud  check request from customer {}", customerID);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
