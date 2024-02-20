package com.mdstore.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OrderItemRequestDTO {
    private Long productID;
    private int quantity;
}
