package com.mdstore.cartservice.web;

import com.mdstore.cartservice.DTOs.CartItemRequestDTO;
import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartRestController {
    private CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> cartList(){
        return cartService.getAllCarts();
    }
    @GetMapping("/carts/{id}")
    public Cart findCartById(@PathVariable Long id){
        return cartService.findCartById(id);
    }

    @PostMapping("/carts/{id}/addItem")
    public ResponseEntity<Object> addToCart(@PathVariable Long id, @RequestBody CartItemRequestDTO cartItemRequestDTO){
        Object result = cartService.addItemToCart(id, cartItemRequestDTO);
        if (result instanceof Cart) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
