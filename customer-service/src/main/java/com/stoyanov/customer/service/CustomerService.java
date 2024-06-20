package com.stoyanov.customer.service;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.application.repository.CustomerRepository;
import com.stoyanov.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return new CustomerDTO(customer);
    }
}