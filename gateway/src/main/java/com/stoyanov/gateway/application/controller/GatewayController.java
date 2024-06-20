package com.stoyanov.gateway.application.controller;


import com.stoyanov.gateway.application.dto.CustomerDTO;
import com.stoyanov.gateway.application.dto.PaymentDTO;
import com.stoyanov.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = gatewayService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO payment = gatewayService.createPayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }
}