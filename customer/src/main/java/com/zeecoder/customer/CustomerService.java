package com.zeecoder.customer;

import com.zeecoder.amqp.RabbitMQMessageProducer;
import com.zeecoder.clients.fraud.FraudCheckResponse;
import com.zeecoder.clients.fraud.FraudClient;
import com.zeecoder.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient fraudClient;

    private final RabbitMQMessageProducer producer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email valid
        //todo check if email not taken
        repository.save(customer); //check if id will bw null

        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                 customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to zeeservice", customer.getFirstName())
        );

        producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );



    }
}
