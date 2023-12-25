package com.mdstore.paymentservice.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long userID;
    private List<OrderItem> orderItems;
    private String orderStatus;
    private Date date;
}
