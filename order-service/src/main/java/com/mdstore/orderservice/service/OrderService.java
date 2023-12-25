package com.mdstore.orderservice.service;

import com.mdstore.orderservice.DTOs.OrderRequestDTO;
import com.mdstore.orderservice.entities.Order;
import com.mdstore.orderservice.mapper.OrderMapper;
import com.mdstore.orderservice.repository.OrderRepository;
import com.mdstore.orderservice.web.OrderRestController;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order save(OrderRequestDTO orderRequestDTO){
        Order order = orderMapper.from(orderRequestDTO);
        System.out.println("Order: "+order);

        return order;
    }
}
