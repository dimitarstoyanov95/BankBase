package org.stoyanov.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stoyanov.payment.application.dto.PaymentDTO;
import org.stoyanov.payment.application.repository.PaymentRepository;
import org.stoyanov.payment.domain.Payment;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        return new PaymentDTO(payment);
    }
}