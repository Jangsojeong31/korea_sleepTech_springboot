package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.constants.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.GetUserResponseDto;
import com.example.korea_sleepTech_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/*
=== UserController VS AuthController ===

1. UserController (로그인한 사용자가 접근할 수 있는 엔드포인트 사용 - '/api/v1/user')
    : 사용자 정보 및 사용자 관련 로직만 처리
    - 회원 정보 조회 (Read)
    - 회원 정보 수정 (Update)
    - 비밀번호 변경 (Update)
    - 회원 탈퇴 (Delete)

2. AuthController (주로 비로그인 사용자도 접근할 수 있는 엔드포인트 사용 - '/api/v1/auth')
    : 인증(Authorizaton)과 인가(Authentication) 관련 로직만 처리
    - 회원가입 (Sign Up)
    - 로그인 (Sign In)
    - 로그아웃 (Sign Out)
    - 토큰 발급 및 갱신
    - OAuth2 (소셜 로그인)

>> SRP(단일 책임 원칙)을 지키기 위해서 분리 사용
 */
@RestController
@RequestMapping(ApiMappingPattern.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // === UserController mapping pattern === //
    private static final String GET_USER_INFO = "/me";
    private static final String PUT_USER_INFO = "/me";
    private static final String DELETE_USER = "/me";

    // 1) 회원 정보 조회
    @GetMapping(GET_USER_INFO)
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getUserInfo(
            @AuthenticationPrincipal String userEmail
            // SecurityContextHolder에 저장된 인증 객체의 principal을 가져와서 사용
            // : 현재 인증된(로그인된) 사용자의 정보를 가져오는 애너테이션
    ) {
        ResponseDto<GetUserResponseDto> response = userService.getUserInfo(userEmail);
        return ResponseEntity.ok(response); // ok 안에 response(body)가 담김
    }

    // 2) 회원 정보 수정 (비밀번호 수정 - 현실에서는 사용 x)
    @PutMapping(PUT_USER_INFO)
    public ResponseEntity<ResponseDto<GetUserResponseDto>> updateUserInfo(
            @AuthenticationPrincipal String userEmail,
            @Valid @RequestBody UserUpdateRequestDto dto
    ) {
        ResponseDto<GetUserResponseDto> response = userService.updateUserInfo(userEmail, dto);
        return ResponseEntity.ok(response);
    }

    // 3) 회원 탈퇴
    @DeleteMapping(DELETE_USER)
    public ResponseEntity<ResponseDto<Void>> deleteUser(
            @AuthenticationPrincipal String userEmail
    ) {
        ResponseDto<Void> response = userService.deleteUser(userEmail);
        return ResponseEntity.noContent().build();
    }

}
