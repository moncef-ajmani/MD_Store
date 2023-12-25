package com.mdstore.orderservice.DTOs;

import com.mdstore.orderservice.entities.OrderItem;
import com.mdstore.orderservice.modal.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {

    private Long userID;
    private List<OrderItemRequestDTO> orderItems;

}
