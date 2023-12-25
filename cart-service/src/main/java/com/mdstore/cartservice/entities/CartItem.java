package com.mdstore.cartservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstore.cartservice.modal.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Transient
    private Product product;
    private Long productID;
    private double SubTotal;
    private double GrandTotal;
    private int quantity;

}
