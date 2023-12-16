package com.mdstore.cartservice.web;

import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}
