package com.zeecoder.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
){

}
