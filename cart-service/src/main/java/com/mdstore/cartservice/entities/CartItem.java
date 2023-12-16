package com.mdstore.cartservice.entities;

import com.mdstore.cartservice.modal.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @Transient
    private Product product;
    private Long productID;
    private double SubTotal;
    private double GrandTotal;

}
