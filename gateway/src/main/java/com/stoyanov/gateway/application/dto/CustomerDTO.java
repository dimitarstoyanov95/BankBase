package com.stoyanov.gateway.application.dto;

import com.stoyanov.gateway.domain.Customer;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }

    // Getters and Setters
}