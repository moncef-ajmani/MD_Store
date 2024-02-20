package com.mdstore.cartservice.service;

import com.mdstore.cartservice.DTOs.CartItemRequestDTO;
import com.mdstore.cartservice.entities.Cart;
import com.mdstore.cartservice.entities.CartItem;
import com.mdstore.cartservice.feignClient.ProductRestClient;
import com.mdstore.cartservice.feignClient.UserRestClient;
import com.mdstore.cartservice.modal.Product;
import com.mdstore.cartservice.modal.User;
import com.mdstore.cartservice.repository.CartItemRepository;
import com.mdstore.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class  CartService {
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
        return cartRepository.findAll().stream().map(c -> findCartById(c.getId())).collect(Collectors.toList());
    }
    public Cart createCart(Long id){
       return cartRepository.save(Cart.builder()
                        .total(0)
                        .userID(id)
                        .date(new Date())
                .build());
    }
    public Cart findCartById(Long id){

        Cart cart = cartRepository.findById(id).get();

        User user = userRestClient.getCustomerById(cart.getUserID());
        cart.setUser(user);
        List<CartItem> cartItems = cartItemRepository.findByCartId(id);

        cartItems.forEach(ci->{
            Product p = productRestClient.getProductById(ci.getProductID());
//            System.out.println(p);
            ci.setProduct(p);
        });
        cart.setCartItems(cartItems);

        return cart;
    }
    public Object addItemToCart(Long id, CartItemRequestDTO cartItemRequestDTO){

        Cart cart = findCartById(id);
        Product product = productRestClient.getProductById(cartItemRequestDTO.getProductID());
        System.out.println(product);
        for(CartItem cartItem: cart.getCartItems()){
            if(cartItem.getProductID().equals(cartItemRequestDTO.getProductID())){
                return "Product Already Exist";
            }
        }
        CartItem cartItem = new CartItem();
        System.out.println(cartItem);

        System.out.println(cartItem);
        cartItem.setProduct(product);
        cartItem.setProductID(product.getId());
        cartItem.setSubTotal(product.getPrice());
        cartItem.setGrandTotal(product.getPrice()*cartItemRequestDTO.getQuantity());
        cartItem.setQuantity(cartItemRequestDTO.getQuantity());
        System.out.println(cartItemRequestDTO.getQuantity()+" - "+cartItem);
        cartItem.setCart(cartRepository.findById(id).get());
        cartItemRepository.save(cartItem);

        return findCartById(id);
    }
}
