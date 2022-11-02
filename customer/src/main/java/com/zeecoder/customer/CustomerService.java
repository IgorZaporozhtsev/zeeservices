package com.zeecoder.customer;

import com.zeecoder.clients.fraud.FraudCheckResponse;
import com.zeecoder.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;
    private final FraudClient client;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email valid
        //todo check if email not taken
        repository.save(customer); //check if id will bw null

        //todo: check if fraudster

        /*

        FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());
        */

        // Use OpenFeign instead. See com.zeecoder.clients.fraud.FraudClient
        FraudCheckResponse response = client.isFraudster(customer.getId());


        if (response.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
    }
}
