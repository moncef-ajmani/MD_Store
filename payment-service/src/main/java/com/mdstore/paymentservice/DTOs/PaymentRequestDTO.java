package com.mdstore.paymentservice.DTOs;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private float amount;
}
