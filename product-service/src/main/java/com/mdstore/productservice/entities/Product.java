package com.mdstore.productservice.entities;

import Enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
    @Enumerated(EnumType.STRING)
    private Category category;

}
