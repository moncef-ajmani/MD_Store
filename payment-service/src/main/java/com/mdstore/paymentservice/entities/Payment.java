package com.mdstore.paymentservice.entities;

import com.mdstore.paymentservice.enums.PaymentStatus;
import com.mdstore.paymentservice.modal.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @Transient
    @JoinColumn(name = "order_id")
    private Order order;
    private Long orderId;

    private float amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
