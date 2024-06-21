package com.stoyanov.customer.service;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.application.mapper.CustomerMapper;
import com.stoyanov.customer.application.repository.CustomerRepository;
import com.stoyanov.customer.domain.exception.CustomerDuplicationException;
import com.stoyanov.customer.domain.exception.CustomerNotFoundException;
import com.stoyanov.customer.domain.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    public CustomerDTO get(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return customerMapper.toDto(customer);
    }

    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDtoList(customers);
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        if (customerRepository.findByEmail(customerDTO.getEmail()) == null) {
            Customer customer = customerMapper.toEntity(customerDTO);
            customerRepository.save(customer);
            return customerMapper.toDto(customer);
        }
        throw new CustomerDuplicationException(customerDTO.getEmail());
    }

    public CustomerDTO update(UUID id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
        updatedCustomer.setId(existingCustomer.getId());
        customerRepository.save(updatedCustomer);

        return customerMapper.toDto(updatedCustomer);
    }

    public void delete(UUID id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.delete(existingCustomer);
    }
}
