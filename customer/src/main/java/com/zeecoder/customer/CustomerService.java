package com.zeecoder.customer;

import com.zeecoder.clients.fraud.FraudCheckResponse;
import com.zeecoder.clients.fraud.FraudClient;
import com.zeecoder.clients.notification.NotificationClient;
import com.zeecoder.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
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
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        //todo: make async. i.e add to queue

        notificationClient.sendNotification(
                new NotificationRequest(
                    customer.getId(),
                    customer.getEmail(),
                    String.format("Hi %s, welcome to zeeservice", customer.getFirstName())
                )
        );

        if (response.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

    }
}
