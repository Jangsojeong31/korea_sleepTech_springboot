package com.example.korea_sleepTech_springboot.config;

import com.example.korea_sleepTech_springboot.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/*
=== WebSecurityConfig ===
: Spring Security를 통해 웹 애플리케이션의 보안을 구성 (보안 환경설정)
- JWT 필터를 적용하여 인증 처리, CORS 및 CSRF 설정을 비활성화
    >> 서버 간의 통신을 원활하게 처리
 */
@Configuration
// : 해당 클래스가 Spring의 설정 클래스로 사용됨을 명시 (Spring이 관리하는 객체를 생성하는 데 사용)
@EnableWebSecurity
// : Spring Security의 웹 보안을 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {
    /*
    JwtAuthenticationFilter (JWT 인증 필터)
    : 요청이 들어올 때 "JWT 토큰을 검증하는 필터" - 검증 후 사용자를 인증
    : UsernamePasswordAuthenticationFilter 이전에 동작
            - JWT 토큰이 유효한지 검사하여 사용자를 인증
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /*
    === corsFilter ===
    : CORS 필터 정책

    cf) CORS(Cross Orgin Resource Sharing)
            - 브라우저에서 다른 도메인으로부터 리소스를 요청할 때 발생하는 보안 정책
            - REST API를 사용할 때 다른 출처(도메인)에서 API에 접근할 수 있도록 허용하는 정책

    CorsFilter 메서드
    : 특정 출처에서 온 HTTP 요청을 허용하거나 거부할 수 있는 필터
    - CORS 관련 설정을 필터링 해주는 역할

     */
    @Bean
    public CorsFilter corsFilter() {
        // 1. UrlBasedCorsConfigurationSource
        // : CORS 정책을 URL 기반으로 "관리하는 객체"
        // - 특정 경로에 따라 CORS 정책을 달리 적용 가능
        // - source 객체를 통해 정책을 사용할 경로 지정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // CORS 관련 세부 설정을 담는 클래스
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // 쿠키를 허용할 지 여부 - 자격 증명을 포함한 요청 허용 여부
        config.addAllowedOriginPattern("*"); // 모든 도메인(출처) 허용 - 어디서든지 요청 가능
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    /*
     * ===== filterChain =====
     * : 보안 필터 체인 정의, 특정 HTTP 요청에 대한 웹 기반 보안 구성
     * - CSRF 보호를 비활성화, CORS 정책을 활성화
     *
     * cf) CSRF(Cross-Site Resource Forgery) 공격
     *   : 사용자가 의도하지 않은 요청을 보내도록 유도하여 악의적인 행동을 수행하는 공격
     *   - REST API는 주로 비동기 방식으로 동작
     *       : 클라이언트에서 토큰(JWT 등)을 사용하여 인증하기 때문에 CSRF 보호가 필요하지 않은 경우가 많음
     *   - Spring Security에서는 기본적으로 CSRF 보호가 활성화되어 있으므로
     *       , RESTful API에서는 명시적으로 비활성화 함
     *
     * cf) CORS 정책: 서로 다른 서버 간의 리소스 상호작용을 위한 정책 - 활성화
     *
     * >>> JWT 필터를 추가하여 인증 요청을 처리
     *   : 특정 경로에 대한 요청은 인증 없이 접근을 허용, 그 외의 요청은 인증이 반드시 필요로 함을 설정
     *
     * @Param: HttpSecurity - 객체를 통해 보안 설정을 관리
     * @Return: SecurityFilterChain - 보안 필터 체인을 구성한 결과를 반환
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())

                // 요청 인증 및 권한 부여 설정
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers( // : 특정 요청과 일치하는 url에 대한 엑세스
                                        // 특정 경로에 대한 엑세스 설정 (토큰없이 가능)
                                        new AntPathRequestMatcher("/api/v1/auth/**")
                                ).permitAll() // : 인증 처리 없이 접근 가능 (누구나 접근 가능 - 인증, 인가 없이 접근 가능)
                                .requestMatchers("api/v1/user/**").hasRole("USER") // USER 권한이 있어야 "api/v1/user/**" 이용 가능
                                .requestMatchers("api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("api/v1/common/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                        // 위에서 설정한 url 이외의 요청에 대해 + 별도의 인가는 필요 X + 인증이 성공된 상태여야 접근 가능 (토큰이 필요)
                )
                // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 이전에 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /*
     * ===== authenticationManager =====
     * : 인증 관리자 관련 설정
     * : 사용자가 입력한 자격 증명 (아이디, 비밀번호)이 올바른지 확인
     * */

    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        // DaoAuthenticationProvider: DB에서 사용자 인증을 처리
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // 비밀번호 검증을 위한 bCryptPasswordEncoder 사용
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);

        // ProviderManager를 반환: DaoAuthenticationProvider를 사용하여 인증 처리
        // - 다중 인증 Provider 관리자를 반환 (사용자 인증 처리 관리자를 관리)
        return new ProviderManager(List.of(authProvider));
    }

    /*
     * BCryptPasswordEncoder: 비밀번호 암호화에 사용되는 클래스
     * - 단방향 해시함수를 사용하여 비밀번호 암호화 함 (복호화 할 수 X)
     *
     * cf) 복호화: 암호를 복구하다
     * */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
