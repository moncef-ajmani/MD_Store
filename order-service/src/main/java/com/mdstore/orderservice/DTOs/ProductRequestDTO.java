package com.mdstore.orderservice.DTOs;

import com.mdstore.orderservice.modal.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private List<Image> images;

}
