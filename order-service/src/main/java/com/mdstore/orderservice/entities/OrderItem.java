package com.mdstore.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstore.orderservice.modal.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @Transient
    private Product product;
    private Long productID;
    private int quantity;

}
