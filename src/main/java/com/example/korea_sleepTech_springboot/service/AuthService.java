package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.auth.PasswordResetRequestDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

//@Service
public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);

    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);

    Mono<ResponseEntity<String>> resetPassword(@Valid PasswordResetRequestDto dto);
}
