package com.stoyanov.customer.application.dto;

import com.stoyanov.customer.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
}