package com.mdstore.orderservice.mapper;

import com.mdstore.orderservice.DTOs.OrderRequestDTO;
import com.mdstore.orderservice.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    private  ModelMapper modelMapper = new ModelMapper();

    public OrderRequestDTO from (Order order){
        return modelMapper.map(order, OrderRequestDTO.class);
    }

    public Order from (OrderRequestDTO orderRequestDTO){
        return modelMapper.map(orderRequestDTO, Order.class);
    }
}
