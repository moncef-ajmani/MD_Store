package com.mdstore.cartservice.service;

import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.entities.CartItem;
import com.mdstore.cartservice.feignClient.ProductRestClient;
import com.mdstore.cartservice.feignClient.UserRestClient;
import com.mdstore.cartservice.repository.CartItemRepository;
import com.mdstore.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRestClient productRestClient;
    private UserRestClient userRestClient;
    private CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, ProductRestClient productRestClient, UserRestClient userRestClient, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRestClient = productRestClient;
        this.userRestClient = userRestClient;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart findCartById(Long id){
        Cart cart = cartRepository.findById(id).get();
        cart.setUser(userRestClient.getCustomerById(cart.getUserID()));
        cart.setCartItems(cartItemRepository.findByCartId(id));
        System.out.println(cartItemRepository.findByCartId(id));
        cart.getCartItems().forEach(ci->{
            ci.setProduct(productRestClient.getProductById(ci.getProductID()));
        });

        return cart;
    }
}
