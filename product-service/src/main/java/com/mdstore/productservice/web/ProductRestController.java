package com.mdstore.productservice.web;

import com.mdstore.productservice.DTOs.ProductDTO;
import com.mdstore.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<ProductDTO> productList(){
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }
}
