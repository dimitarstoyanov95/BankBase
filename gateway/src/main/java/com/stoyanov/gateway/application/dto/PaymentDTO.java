package com.stoyanov.gateway.application.dto;

import com.stoyanov.gateway.domain.Payment;
import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Long customerId;
    private Double amount;

    public PaymentDTO() {}

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.customerId = payment.getCustomerId();
        this.amount = payment.getAmount();
    }

    // Getters and Setters
}