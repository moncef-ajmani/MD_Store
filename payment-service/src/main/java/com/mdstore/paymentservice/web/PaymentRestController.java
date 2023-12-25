package com.mdstore.paymentservice.web;

import com.mdstore.paymentservice.DTOs.PaymentRequestDTO;
import com.mdstore.paymentservice.entities.Payment;
import com.mdstore.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {
    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Payment processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        Payment payment = paymentService.processPayment(paymentRequestDTO);
        return payment;
    }

    public Payment getPaymentByOrder(@PathVariable Long id){
        return paymentService.getPaymentByOrder(id);
    }
}
