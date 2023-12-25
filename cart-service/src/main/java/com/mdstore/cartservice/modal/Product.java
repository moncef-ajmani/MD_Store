package com.mdstore.cartservice.modal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private List<Image> images;
}
