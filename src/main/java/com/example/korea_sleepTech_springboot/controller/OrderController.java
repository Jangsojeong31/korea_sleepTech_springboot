package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.order.OrderRequestDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.ORDER_API)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private static final String PLACE_ORDER = "/place";

    @PostMapping(PLACE_ORDER)
    public ResponseEntity<ResponseDto<OrderResponseDto>> placeOrder(
            @AuthenticationPrincipal String userEmail, // 로그인하고 있는 사용자의 id
            @Valid @RequestBody OrderRequestDto dto
    ) {
       ResponseDto<OrderResponseDto> response = orderService.placeOrder(userEmail, dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
