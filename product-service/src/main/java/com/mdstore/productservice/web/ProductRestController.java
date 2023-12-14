package com.mdstore.productservice.web;

import com.mdstore.productservice.DTOs.ProductRequestDTO;
import com.mdstore.productservice.DTOs.ProductResponseDTO;
import com.mdstore.productservice.exception.ProductNotFoundException;
import com.mdstore.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<ProductResponseDTO> productList(){
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO responseDTO =  productService.save(productDTO);
        return ResponseEntity.created(URI.create("/product/"+responseDTO.getId())).body(responseDTO);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO responseDTO = productService.update(id, productDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        String successMessage = "Product with ID " + id + " deleted successfully";
        return ResponseEntity.ok(successMessage);
    }
}
