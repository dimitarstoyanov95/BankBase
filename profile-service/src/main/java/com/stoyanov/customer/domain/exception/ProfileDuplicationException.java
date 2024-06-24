package com.stoyanov.customer.domain.exception;

public class ProfileDuplicationException extends RuntimeException {

    public ProfileDuplicationException(String email) {
        super("Profile with email %s already exists within the database".formatted(email));
    }
}
