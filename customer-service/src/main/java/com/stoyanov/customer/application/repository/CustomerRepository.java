package com.stoyanov.customer.application.repository;


import com.stoyanov.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByEmail(String email);

}