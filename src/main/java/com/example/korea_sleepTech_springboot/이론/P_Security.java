package com.example.korea_sleepTech_springboot.이론;

public class P_Security {
}

/*
=== Spring Security (보안) ===
    : Spring Framework 기반 애플리케이션에서 보안(인증, 인가, 권한)을 담당하는 보안 프레임워크
    >> 다양한 애너테이션으로 CSRF 공격, 세션 고정 공격을 방어
    >> 요청 헤더에 포함된 보안 처리도 가능

    cf) CSRF(Cross-Site Request Forgery)
        : 자신의 의지와는 상관없이 특정 웹 사이트에 공격자가 원하는 요청을 보내도록 유도하는 웹 보안 취약점

    1. 인증 (Authentication)
        : 사용자가 누구인지 확인하는 과정, 신원 입증 과정
        ex) 사용자가 로그인 폼에 ID와 비밀번호를 입력하면
            , 이를 기반으로 사용자가 주장하는 인물인지 확인

    2. 인가 (Authorization)
        : 인증된 사용자가 특정 리소스에 접근할 수 있는 권한인지 확인하는 과정
        ex) 관리자가 특정 페이지에 접근할 수 있도록 설정

=== Spring Security 구조 ===
    : 필터(Filter)를 기반으로 동작

 1) SecurityFilterChain
     : 스프링 시큐리티의 요청에 대한 보안 처리 과정에서 여러 필터를 순차적으로 적용시킴
     >> 각 필터에서 인증, 인가와 관련된 작업을 처리

     : 아래의 순서대로 필터를 적용시킴

     - SecurityContextPersistenceFilter
     - LogoutFilter
     * UsernamePasswordAuthenticationFilter *
          : 아이디와 패스워드가 넘어오면 인증 요청을 위임하는 인증 관리자 역할
          : 폼 기반 로그인할 때 사용되는 필터
          : 성공 시 AuthenticationSuccessHandler
          : 실패 시 AuthenticationFailureHandler 실행

     - DefaultLoginPageGeneratingFilter
     - BasicAuthenticationFilter
     - RequestCacheAwareFilter
     - SecurityContextHolderAwareRequestFilter
     - AnonymousAuthenticationFilter
     - SessionManagementFilter
     - ExceptionTranslationFilter
     * FilterSecurityInterceptor *
          : 권한 부여 처리를 위임, 접근 제어 결정을 쉽게 하는 접근 결정 관리자 역할
          : 이미 사용자가 인증되어 있기 때문에 유효한 사용자인지 확인 가능
          >> '인가' 관련 설정

=== Spring Security 인증 처리 절차 ===
    1. HTTP 요청
        : 사용자가 폼에 ID와 PW를 입력하고 전송
    2. Authentication Filter 동작
        : 전달된 ID와 비밀번호의 유효성 검사를 진행
    3. UsernamePasswordAuthenticationToken 생성
        : 유효성 검사가 완료되면, 인증 토큰 생성 (인증용 객체)
        >> AuthenticationManager로 전달
    4. AuthenticationProvider 전달
        : AuthenticationProvider는 UserDetailsService를 통해 사용자 정보를 조회
    5. DB에서 사용자 정보 로딩
        : 사용자 정보를 DB에서 로딩하여 UserDetails 객체로 생성
    6. 입력된 정보와 DB 정보가 일치하면 인증이 완료
    7. SecurityContextHolder에 Authentication (인증정보) 저장

 */
