package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;

public class UserSignInResponseDto {
    private String token;
    private User user;
    private int exprTime; // 만료시간
}
