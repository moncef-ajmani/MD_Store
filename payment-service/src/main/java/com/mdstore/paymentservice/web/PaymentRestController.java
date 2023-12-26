package com.mdstore.paymentservice.web;

import com.mdstore.paymentservice.DTOs.PaymentRequestDTO;
import com.mdstore.paymentservice.entities.Payment;
import com.mdstore.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentRestController {
    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/payments")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return new ResponseEntity<>(paymentService.processPayment(paymentRequestDTO), HttpStatus.CREATED);
    }
    @GetMapping("/payments/order/{id}")
    public ResponseEntity<Payment> getPaymentByOrder(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.getPaymentByOrder(id),HttpStatus.OK);
    }
}
