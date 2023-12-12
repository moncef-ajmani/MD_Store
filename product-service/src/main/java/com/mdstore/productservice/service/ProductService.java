package com.mdstore.productservice.service;

import com.mdstore.productservice.DTOs.ProductDTO;
import com.mdstore.productservice.entities.Image;
import com.mdstore.productservice.entities.Product;
import com.mdstore.productservice.mapper.ProductMapper;
import com.mdstore.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::from).collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO productDTO){
        Product product = productMapper.from(productDTO);
        System.out.println(product.toString());
        for(Image image: product.getImages()){
            image.setProduct(product);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.from(savedProduct);
    }



}
