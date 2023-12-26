package com.mdstore.orderservice.modal;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Long orderId;
    private float amount;
    private String status;
}
