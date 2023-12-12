package com.mdstore.productservice.mapper;

import com.mdstore.productservice.DTOs.ProductDTO;
import com.mdstore.productservice.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public ProductDTO from (Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product from (ProductDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }

}
