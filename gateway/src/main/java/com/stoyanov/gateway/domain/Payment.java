package com.stoyanov.gateway.domain;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Long customerId;
    private Double amount;
}
