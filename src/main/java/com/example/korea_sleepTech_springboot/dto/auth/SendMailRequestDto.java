package com.example.korea_sleepTech_springboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRequestDto {
    @NotBlank
    @Email // email 형식 유효성 검사
    private String email;


}
