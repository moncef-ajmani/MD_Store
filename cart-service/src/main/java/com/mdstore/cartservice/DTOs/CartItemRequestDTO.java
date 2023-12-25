package com.mdstore.cartservice.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.modal.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class CartItemRequestDTO {
    private Long productID;
    private int quantity;
}
