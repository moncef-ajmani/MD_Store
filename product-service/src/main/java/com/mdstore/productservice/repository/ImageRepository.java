package com.mdstore.productservice.repository;

import com.mdstore.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Product,Long> {
}
