package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
// 인덱스 명시 옵션 (indexes)
@Table(name = "products", indexes = @Index(name = "idx_product_name", columnList = "name"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private LocalDateTime createdAt = LocalDateTime.now();
}
