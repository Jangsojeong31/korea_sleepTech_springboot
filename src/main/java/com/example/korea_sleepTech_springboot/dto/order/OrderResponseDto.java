package com.example.korea_sleepTech_springboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private String message;
    private List<OrderedItemInfo> orderedItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderedItemInfo { // 뷰
        private Long productId;
        private String productName;
        private int quantity;
        private int price;
        private int total;

    }
}
