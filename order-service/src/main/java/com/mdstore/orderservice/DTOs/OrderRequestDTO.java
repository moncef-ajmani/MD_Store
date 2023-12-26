package com.mdstore.orderservice.DTOs;

import com.mdstore.orderservice.entities.OrderItem;
import com.mdstore.orderservice.modal.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long userID;
    private List<OrderItemRequestDTO> orderItems;

}
