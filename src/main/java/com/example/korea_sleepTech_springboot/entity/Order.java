package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User:Oder = 1:N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String orderStatus = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Builder
    public Order(User user, String orderStatus, LocalDateTime createdAt, List<OrderItem> items) {
        this.user = user;
        this.orderStatus = orderStatus != null ? orderStatus : "PENDING";
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.items = items != null ? items : new ArrayList<>();
    }


}
