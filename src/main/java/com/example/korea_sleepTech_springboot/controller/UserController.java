package com.example.korea_sleepTech_springboot.controller;

import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
}
