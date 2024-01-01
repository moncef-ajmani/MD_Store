package com.mdstore.orderservice.mapper;

import com.mdstore.orderservice.DTOs.OrderItemRequestDTO;
import com.mdstore.orderservice.DTOs.OrderRequestDTO;
import com.mdstore.orderservice.entities.Order;
import com.mdstore.orderservice.entities.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public OrderItemRequestDTO from (Order order){
        return modelMapper.map(order, OrderItemRequestDTO.class);
    }

    public OrderItem from (OrderItemRequestDTO orderItemRequestDTO){
        return modelMapper.map(orderItemRequestDTO, OrderItem.class);
    }
}
