package com.zeecoder.notification;

import com.zeecoder.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "com.zeecoder.notification",
                "com.zeecoder.amqp"
        }
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }


    // commandLineRunner, scanBasePackages for testing purpose. Publish message directly from Notification service

    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig config){
        return args -> {
            producer.publish("foo", config.getInternalExchange(), config.getInternalNotificationRoutingKey());
        };
    }
}
