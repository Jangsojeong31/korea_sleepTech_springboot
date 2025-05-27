package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // product 하나당 하나의 stock
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
