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

@RestController
public class OrderRestController {
    private final OrderService orderService;
    private final PaymentRestClient paymentRestClient;
    private final OrderMapper orderMapper;
    private final ProductRestClient productRestClient;
    private final UserRestClient userRestClient;

    public OrderRestController(OrderService orderService, PaymentRestClient paymentRestClient, OrderMapper orderMapper, ProductRestClient productRestClient, UserRestClient userRestClient){
        this.orderService = orderService;
        this.paymentRestClient = paymentRestClient;
        this.orderMapper = orderMapper;
        this.productRestClient = productRestClient;
        this.userRestClient = userRestClient;

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
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){

        Order order = orderMapper.from(orderRequestDTO);
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
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        order.setOrderStatus("Done");
        order.setDate(java.sql.Date.valueOf(LocalDate.now()));
        order.setUser(userRestClient.getCustomerById(order.getUserID()));
        order.setUserID(order.getUserID());
        System.out.println(order);
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(order.getId(),amount);
        ResponseEntity<Payment> paymentResponse = paymentRestClient.processPayment(paymentRequestDTO);
        if(paymentResponse.getStatusCode() == HttpStatus.CREATED){
            System.out.println(paymentRequestDTO);
//            orderService.save(new OrderRequestDTO(order.getUserID(),order.getOrderItems()));
            return new ResponseEntity<>(order,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
