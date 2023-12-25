package com.mdstore.productservice.service;

import com.mdstore.productservice.DTOs.ProductRequestDTO;
import com.mdstore.productservice.DTOs.ProductResponseDTO;
import com.mdstore.productservice.entities.Image;
import com.mdstore.productservice.entities.Product;
import com.mdstore.productservice.exception.ProductNotFoundException;
import com.mdstore.productservice.mapper.ProductMapper;
import com.mdstore.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }



    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::from).collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id){
        return productMapper.from(productRepository.findById(id).get());
    }

    public ProductResponseDTO save(ProductRequestDTO productDTO){
        Product product = productMapper.from(productDTO);
        System.out.println(product.toString());
        for(Image image: product.getImages()){
            image.setProduct(product);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.from(savedProduct);
    }

    public ProductResponseDTO update(Long productId,ProductRequestDTO updatedProductDTO){
        if (!productRepository.existsById(productId)) {
            // Handle the case where the product to update is not found
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }

        // Map the updated data to the existing product
        Product existingProduct = productRepository.findById(productId).orElseThrow();
        productMapper.update(existingProduct,updatedProductDTO);

        // Set the product for each image in the updated product's image list
        for (Image image : updatedProductDTO.getImages()) {
            image.setProduct(existingProduct);
        }

        // Save the updated product and return the result
        Product savedProduct = productRepository.save(existingProduct);
        return productMapper.from(savedProduct);
    }

    public void delete(Long productId) {
        // Check if the product with the given ID exists
        if (!productRepository.existsById(productId)) {
            // Handle the case where the product to delete is not found
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }

        // Delete the product by ID
        productRepository.deleteById(productId);
    }
}
