package com.mdstore.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstore.orderservice.modal.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Order order;
    @Transient
    private Product product;
    private Long productID;
    private int quantity;

}
