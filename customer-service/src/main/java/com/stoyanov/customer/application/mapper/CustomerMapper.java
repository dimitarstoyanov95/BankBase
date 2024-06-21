package com.stoyanov.customer.application.mapper;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDTO toDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        return dto;
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public List<CustomerDTO> toDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Customer> toEntityList(List<CustomerDTO> customers) {
        return customers.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

