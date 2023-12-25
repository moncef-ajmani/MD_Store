package com.mdstore.paymentservice.service;

import com.mdstore.paymentservice.DTOs.PaymentRequestDTO;
import com.mdstore.paymentservice.entities.Payment;
import com.mdstore.paymentservice.enums.PaymentStatus;
import com.mdstore.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(PaymentRequestDTO paymentRequestDTO){
//      Use Payment Gateway
        Payment payment = new Payment();
        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setStatus(PaymentStatus.APPROVED);

        return paymentRepository.save(payment);
    }
    public Payment getPaymentByOrder(Long orderId){
        return paymentRepository.findByOrderId(orderId).get();
    }
}
