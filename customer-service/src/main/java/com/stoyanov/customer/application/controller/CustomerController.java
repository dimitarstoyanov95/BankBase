package com.stoyanov.customer.application.controller;

import com.stoyanov.customer.application.dto.CustomerDTO;
import com.stoyanov.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable UUID id) {
        CustomerDTO customer = customerService.get(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerDTO> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.create(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable UUID id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.update(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        customerService.delete(id);
    }
}
