package com.stoyanov.customer.application.dto;

import com.stoyanov.customer.domain.Customer;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}