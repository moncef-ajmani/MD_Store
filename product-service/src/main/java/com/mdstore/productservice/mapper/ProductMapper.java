package com.mdstore.productservice.mapper;

import com.mdstore.productservice.DTOs.ProductRequestDTO;
import com.mdstore.productservice.DTOs.ProductResponseDTO;
import com.mdstore.productservice.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductResponseDTO from (Product product){
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product from (ProductRequestDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }

    public void update(Product product, ProductRequestDTO productDTO){
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImages(productDTO.getImages());
        product.setCategory(productDTO.getCategory());
    }

}
