package com.mdstore.productservice.web;

import Enums.Category;
import com.mdstore.productservice.DTOs.ProductRequestDTO;
import com.mdstore.productservice.DTOs.ProductResponseDTO;
import com.mdstore.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponseDTO> productList(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO responseDTO =  productService.save(productDTO);
        return ResponseEntity.created(URI.create("/product/"+responseDTO.getId())).body(responseDTO);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO responseDTO = productService.update(id, productDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        String successMessage = "Product with ID " + id + " deleted successfully";
        return ResponseEntity.ok(successMessage);
    }


}
