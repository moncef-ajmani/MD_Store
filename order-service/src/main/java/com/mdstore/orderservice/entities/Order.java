package com.mdstore.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstore.orderservice.modal.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity(name = "`order`")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private User user;
    private Long userID;
    @Transient
    @OneToMany(mappedBy = "`order`", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private String orderStatus;
    private Date date;
}
