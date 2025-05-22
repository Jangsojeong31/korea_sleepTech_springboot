package com.example.korea_sleepTech_springboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendMailRequestDto {
    @NotBlank
    @Email // email 형식 유효성 검사
    private String email;


}
