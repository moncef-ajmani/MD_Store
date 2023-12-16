package com.mdstore.cartservice.repository;

import com.mdstore.cartservice.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCartId(Long cartId);
}
