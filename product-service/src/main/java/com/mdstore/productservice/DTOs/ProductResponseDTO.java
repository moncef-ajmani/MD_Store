package com.mdstore.productservice.DTOs;

import Enums.Category;
import com.mdstore.productservice.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private List<Image> images;

    private Category category;

}
