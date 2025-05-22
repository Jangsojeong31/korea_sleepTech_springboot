package com.example.korea_sleepTech_springboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetRequestDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmNewPassword;
}
