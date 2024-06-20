package org.stoyanov.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    private Long id;
    private Double amount;

    // Getters and Setters
}