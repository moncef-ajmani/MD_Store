package com.mdstore.productservice.DTOs;

import com.mdstore.productservice.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductRequestDTO {
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private List<Image> images;

}
