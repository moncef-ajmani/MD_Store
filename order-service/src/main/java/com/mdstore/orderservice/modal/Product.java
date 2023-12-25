package com.mdstore.orderservice.modal;

import lombok.Data;

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
