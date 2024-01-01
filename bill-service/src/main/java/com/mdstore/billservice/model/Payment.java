package com.mdstore.billservice.model;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Long orderId;
    private float amount;
    private String status;
}
