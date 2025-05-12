package com.example.korea_sleepTech_springboot.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword; // 비밀번호 확인 (비즈니스 로직에서만 사용할 필드)
}
