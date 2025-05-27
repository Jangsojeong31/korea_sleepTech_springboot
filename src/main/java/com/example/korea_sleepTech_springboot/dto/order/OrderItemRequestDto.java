package com.example.korea_sleepTech_springboot.dto.order;

import lombok.Getter;

@Getter
public class OrderItemRequestDto {
    private Long productId;
    private int quantity;
}
