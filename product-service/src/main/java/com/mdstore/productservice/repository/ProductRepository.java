package com.mdstore.productservice.repository;

import com.mdstore.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface ProductRepository extends JpaRepository<Product,Long> {
}
