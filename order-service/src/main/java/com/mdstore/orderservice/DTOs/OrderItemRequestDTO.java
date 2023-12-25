package com.mdstore.orderservice.DTOs;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Long productID;
    private int quantity;
}
