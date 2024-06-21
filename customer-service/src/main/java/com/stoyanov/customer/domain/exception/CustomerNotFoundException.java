package com.stoyanov.customer.domain.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(UUID id) {
        super("Customer with id %s does not exist".formatted(id));
    }
}
