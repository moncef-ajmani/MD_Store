package com.mdstore.billservice.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mdstore.billservice.entities.Bill;
import com.mdstore.billservice.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductItemRequestDTO {
    private Long productID;
    private int quantity;
}
