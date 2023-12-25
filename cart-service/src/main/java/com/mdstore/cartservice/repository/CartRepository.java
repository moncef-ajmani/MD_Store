package com.mdstore.cartservice.repository;

import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
