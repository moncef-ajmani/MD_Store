package com.mdstore.cartservice.modal;

import jakarta.persistence.*;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private List<Image> images;
}
