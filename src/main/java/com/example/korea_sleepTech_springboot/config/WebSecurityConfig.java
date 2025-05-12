package com.example.korea_sleepTech_springboot.config;

import com.example.korea_sleepTech_springboot.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*
=== WebSecurityConfig ===
: Spring Security를 통해 웹 애플리케이션의 보안을 구성 (보안 환경설정)
- JWT 필터를 적용하여 인증 처리, CORS 및 CSRF 설정을 비활성화
    >> 서버 간의 통신을 원활하게 처리
 */
@Configuration
// : 해당 클래스가 Spring의 설정 클래스로 사용됨을 명시 (Spring이 관리하는 객체를 생성하는 데 사용)
@EnableWebSecurity
// : Spring Security의 웹 보완을 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {
    /*
    JwtAuthenticationFilter (JWT 인증 필터)
    : 요청이 들어올 때 "JWT 토큰을 검증하는 필터" - 검증 후 사용자를 인증
    : UsernamePasswordAuthenticationFilter 이전에 동작
            - JWT 토큰이 유효한지 검사하여 사용자를 인증
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

}
