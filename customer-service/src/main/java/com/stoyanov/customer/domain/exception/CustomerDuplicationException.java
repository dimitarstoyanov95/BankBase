package com.stoyanov.customer.domain.exception;

import java.util.UUID;

public class CustomerDuplicationException extends RuntimeException {

    public CustomerDuplicationException(String email) {
        super("Customer with email %s already exists within the database".formatted(email));
    }
}
