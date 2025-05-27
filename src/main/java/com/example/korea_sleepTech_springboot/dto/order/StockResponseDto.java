package com.example.korea_sleepTech_springboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDto {
    private Long productId;
    private int quantity;
}
