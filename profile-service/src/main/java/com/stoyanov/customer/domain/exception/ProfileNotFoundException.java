package com.stoyanov.customer.domain.exception;

import java.util.UUID;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(UUID id) {
        super("Profile with id %s does not exist".formatted(id));
    }
}
