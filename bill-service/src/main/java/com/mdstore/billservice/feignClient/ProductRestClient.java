package com.mdstore.billservice.feignClient;

import com.mdstore.billservice.DTOs.ProductRequestDTO;
import com.mdstore.billservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name="PRODUCT-SERVICE")

public interface ProductRestClient {
    @GetMapping("/products")
    List<Product> getProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);

    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productDTO);
}
