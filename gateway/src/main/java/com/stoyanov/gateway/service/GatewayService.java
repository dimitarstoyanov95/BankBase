package com.stoyanov.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GatewayService {

    @Autowired
    private RestTemplate restTemplate;

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = restTemplate.getForObject("http://localhost:8081/customers/" + id, Customer.class);
        return new CustomerDTO(customer);
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setCustomerId(paymentDTO.getCustomerId());

        Payment createdPayment = restTemplate.postForObject("http://localhost:8082/payments", payment, Payment.class);
        return new PaymentDTO(createdPayment);
    }
}