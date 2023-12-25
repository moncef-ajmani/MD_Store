package com.mdstore.orderservice.web;

import com.mdstore.orderservice.DTOs.OrderRequestDTO;
import com.mdstore.orderservice.entities.Order;
import com.mdstore.orderservice.mapper.OrderMapper;
import com.mdstore.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService){
        this.orderService = orderService;

    }

    @GetMapping("/orders")
    public List<Order> ordersList(){
        return orderService.getAllOrders();
    }

//    @PostMapping("/orders")
//    public Order createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
//
//        Order order = orderService.save(orderRequestDTO);
////        payment process
////        if payment valide save order
////        return order
//    }
}
