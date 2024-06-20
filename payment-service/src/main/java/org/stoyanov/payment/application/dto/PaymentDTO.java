package org.stoyanov.payment.application.dto;

import lombok.Data;
import org.stoyanov.payment.domain.Payment;

@Data
public class PaymentDTO {
    private Long id;
    private Double amount;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
    }

    // Getters and Setters
}