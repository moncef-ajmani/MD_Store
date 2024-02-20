package com.mdstore.billservice.feignClient;

import com.mdstore.billservice.DTOs.PaymentRequestDTO;
import com.mdstore.billservice.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PAYMENT-SERVICE")
public interface PaymentRestClient {
    @GetMapping("/payments")
    ResponseEntity<Payment> processPayment(PaymentRequestDTO paymentRequestDTO);

    @GetMapping("/payments/order/{id}")
    ResponseEntity<Payment> getPaymentByOrder(@PathVariable Long id);
}
