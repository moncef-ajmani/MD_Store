package com.mdstore.orderservice.service;

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
import com.mdstore.orderservice.repository.OrderRepository;
import com.mdstore.orderservice.web.OrderRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private  OrderMapper orderMapper;
    private OrderItemMapper orderItemMapper;
    private final PaymentRestClient paymentRestClient;

    private final ProductRestClient productRestClient;
    private final UserRestClient userRestClient;



    public OrderService(PaymentRestClient paymentRestClient, OrderMapper orderMapper, ProductRestClient productRestClient, OrderItemMapper orderItemMapper , UserRestClient userRestClient
    ,OrderRepository orderRepository){
        this.paymentRestClient = paymentRestClient;
        this.orderMapper = orderMapper;
        this.productRestClient = productRestClient;
        this.userRestClient = userRestClient;
        this.orderItemMapper = orderItemMapper;
        this.orderRepository = orderRepository;

    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public ResponseEntity<?> save(OrderRequestDTO orderRequestDTO){
        Order order = Order.builder()
                .userID(orderRequestDTO.getUserID())
                .orderItems(orderRequestDTO.getOrderItems().stream().map(orderItemMapper::from).collect(Collectors.toList()))
                .build();
        float amount = 0;
        for(OrderItem orderItem: order.getOrderItems()){
            Product p = productRestClient.getProductById(orderItem.getProductID());
            if(p.getStockQuantity() - orderItem.getQuantity() > 0) {
                amount += p.getPrice() * orderItem.getQuantity();
                p.setStockQuantity(p.getStockQuantity()-orderItem.getQuantity());
                productRestClient.updateProduct(p.getId(),new ProductRequestDTO(
                        p.getName(),p.getPrice(),p.getDescription(),p.getStockQuantity(),p.getImages()
                ));
            }else{
                String errorMessage = "Emtpy Stock for product: "+p.getName();
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
        }
        order.setOrderStatus("Done");
        order.setDate(java.sql.Date.valueOf(LocalDate.now()));
        order.setAmount(amount);
        order.setUser(userRestClient.getCustomerById(order.getUserID()));
        System.out.println(order);

        order.setUserID(order.getUserID());
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(order.getId(),amount);
        ResponseEntity<Payment> paymentResponse = paymentRestClient.processPayment(paymentRequestDTO);

        if(paymentResponse.getStatusCode() == HttpStatus.CREATED){

            Order savedOrder = orderRepository.save(order);



            return new ResponseEntity<>(savedOrder,HttpStatus.CREATED);
        }

//      if product in stock save the order and update product else return error message
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
