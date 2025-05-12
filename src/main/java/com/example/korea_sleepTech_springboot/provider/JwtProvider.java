package com.example.korea_sleepTech_springboot.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/*
=== JwtProvider 클래스 ===
: JWT(JSON Web Token) 토큰을 생성하고 검증하고 역할

cf) JWT
    : 사용자 정보를 암호화된 토큰으로 저장, 서버에 요청할 떄마다 전달 가능 (사용자 정보 확인용)
    : 주로 로그인 인증에 사용

- HS256 암호화 알고리즘을 사용하여 JWT 서명
    >> 비밀키는 Base64로 인코딩 지정 - 환경변수(jwt.secret)
    >> JWT 만료 기간은 10시간 지정 - 환경변수(jwt.expiration)
 */
/*
@Bean vs @Component
    @Bean: 메서드 레벨에서 선언, 반환되는 객체를 개발자가 수동으로 빈 등록
    @Component: 클래스 레벨에서 선언, 스프링 컨타임 시 컴포넌트 스캔을 통해 자동으로 빈을 찾고 등록 (의존성 주입)
 */
@Component // 스프링 컨테이너에서 해당 클래스를 빈으로 관리하기 위해 사용
public class JwtProvider {

    // 환경 변수에 지정한 비밀키와 만료 시간을 가져옴
    private final Key key; // JWT 서명에 사용할 암호키 저장 변수
    private final int jwtExpirationMs; // JWT 토큰의 만료 시간을 저장

    public JwtProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") int jwtExpirationMs
    ){
        // 생성자: JWTProvider 객체 생성 시 비밀 키와 만료시간을 초기화

        // Base64로 인코딩된 비밀키를 디코딩하여 HMAC-SHA 알고리즘으로 암호화된 키 생성
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.jwtExpirationMs = jwtExpirationMs;
    }

    /*
    generateJwtToken
    : JWT 생성 메서드
    : 사용자 정보를 받아 JWT 토큰을 생성하는 메서드

    @Param: 사용자 정보
    @Return: 생성된 JWT 토큰 문자열
     */
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .claim("username", username) // 클레임에 사용자 ID를 저장 (User 엔티티의 id가 아니라, 로그인 시 사용할 사용자 식별자)
                .setIssuedAt(new Date()) // 현재 시간을 기준으로 토큰 발생 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // 현재 시간에 만료 시간을 더해 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256) // HMAC-SHA256 알고리즘으로 생성된 비밀키로 서명
                .compact(); // JWT를 최종적으로 직렬화하여 문자열로 반환

    }

    /*
    === removeBearer ===
    JWT 에서 Bearer 접두사 제거
    @Param: 접두사가 포함된 JWT 문자열
    @Return: "Bearer "이 제거된 JWT

    cf) Bearer(소유자)
           : 클라이언트에서 JWT 토큰 내에 토큰의 소유자 정보와 권한이 담겨있음을 명시
     */
    public String removeBearer(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid JWT token format");
        }

        // substring(N): N부터 끝까지의 문자열을 리턴
        // substring(A, B): A 이상 B 미만까지의 문자열을 리턴
        return bearerToken.substring("Bearer ".length());
    }

    /*
    == isValidToken ==
    : JWT 유효성 검증
    - Bearer이 제거된 서명키 자체의 유효성을 검증

    @Param: token - JWT 토큰(Bearer 없음)
    @Return: 유효하면 true, 유효하지 않으면 false
     */

    public boolean isValidToken(String token) {
        try {
            getClaims(token); // JWT 클레임을 가져오면서 유효성 검증
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
    === getClaims ===
    : JWT 클레임 정보를 가져오기

    @Param: token
    @Return: 클레임 정보
     */

    public Claims getClaims(String token){
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key) // JWT 파서에 서명에 사용된 비밀키 설정
                .build();

        // JWT를 파싱하여 클레임 정보(body)를 반환
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /*
    === getUsernameFromJwt ===
    : JWT 검증 + 사용자 ID 추출

    @Param: JWT 토큰
    @Return: 사용자 ID
     */
    public String getUsernameFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("username", String.class); // 클레임에서 username 값을 문자열 형태로 반환
    }
}
