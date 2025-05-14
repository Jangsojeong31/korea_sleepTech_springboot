package com.example.korea_sleepTech_springboot.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
}
