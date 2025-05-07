package com.example.korea_sleepTech_springboot.시험.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
}
