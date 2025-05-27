package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.order.OrderRequestDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {
    ResponseDto<OrderResponseDto> placeOrder(String userEmail, @Valid OrderRequestDto dto);

    ResponseDto<List<OrderResponseDto.OrderedItemInfo>> getOrderSummary(Long orderId);
}
