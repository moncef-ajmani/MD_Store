package com.mdstore.orderservice.web;

import com.mdstore.orderservice.DTOs.OrderItemRequestDTO;
import com.mdstore.orderservice.DTOs.OrderRequestDTO;
import com.mdstore.orderservice.DTOs.PaymentRequestDTO;
import com.mdstore.orderservice.DTOs.ProductRequestDTO;
import com.mdstore.orderservice.entities.Order;
import com.mdstore.orderservice.entities.OrderItem;
import com.mdstore.orderservice.feignClient.PaymentRestClient;
import com.mdstore.orderservice.feignClient.ProductRestClient;
import com.mdstore.orderservice.feignClient.UserRestClient;
import com.mdstore.orderservice.mapper.OrderItemMapper;
import com.mdstore.orderservice.mapper.OrderMapper;
import com.mdstore.orderservice.modal.Payment;
import com.mdstore.orderservice.modal.Product;
import com.mdstore.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderRestController {
    private final OrderService orderService;
    private final PaymentRestClient paymentRestClient;
    private  OrderMapper orderMapper;
    private final ProductRestClient productRestClient;
    private final UserRestClient userRestClient;
    private  OrderItemMapper orderItemMapper;

    public OrderRestController(OrderService orderService, PaymentRestClient paymentRestClient, OrderMapper orderMapper, ProductRestClient productRestClient, OrderItemMapper orderItemMapper , UserRestClient userRestClient){
        this.orderService = orderService;
        this.paymentRestClient = paymentRestClient;
        this.orderMapper = orderMapper;
        this.productRestClient = productRestClient;
        this.userRestClient = userRestClient;
        this.orderItemMapper = orderItemMapper;

    }

    @GetMapping("/orders")
    public List<Order> ordersList(){
        return orderService.getAllOrders();
    }

    public float calculateAmount(List<OrderItemRequestDTO> orderItems){
        float amount = 0;

        return 0;
    }
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){

        return orderService.save(orderRequestDTO);
    }
}
