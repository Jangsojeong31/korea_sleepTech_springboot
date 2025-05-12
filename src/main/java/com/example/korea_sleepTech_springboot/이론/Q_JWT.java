package com.example.korea_sleepTech_springboot.이론;

public class Q_JWT {
}
/*
=== JWT(JSON Web Token) ===

    1. JWT란?
        : JSON 객체를 이용하여 사용자의 인증 정보를 안전하게 전달하는 토크 기반 인증 방식
        : 서버 >> 클라이언트에게 발급하는 디지털 서명이 된 토큰
        : 사용자의 인증 상태를 유지하여 서버에 전달
        - Stateless 방식으로 서버에서 세션을 유지할 필요 x (무상태성)

    2. JWT 특징
        - 인증(Authentication)
            : 사용자가 로그인하면 서버는 사용자 정보를 기반으로 JWT를 발급
            >> 이후, 사용자가 토큰을 서버에 전달하며 인증을 진행

        - 인가(Authorization)
            : JWT에는 사용자 권한 정보가 담길 수 있음
            >> API 또는 리소스에 대한 접근 권한을 부여

        cf) Stateless(무상태성)
            : 서버가 세션을 저장할 필요 x, 클라이언트가 관리하기 때문에 확장성이 뛰어남

    3. 발급받은 JWT를 이용한 인증 방법
        : HTTP 요청 헤더 중에 Authorization 키값에 "Bearer " + JWT 토큰값을 전달

    4. Bearer Token
        : OAuth 2.0 인증 방식 중 하나
        - API 요청 시 클라이언트가 서버에 자신이 인증된 사용자임을 증명하기 위해 사용되는 인증 토큰
        - 토큰을 가지고 있는 자가(해당 클라이언트가) 인증된 사용자임을 뜻함

    5. JWT의 구조
        : 마침표(.)를 기준으로 헤더(header), 내용(payload), 서명(signature)로 이루어짐
        aaaa(헤더).bbbb(내용).cccc(서명)
        1) 헤더
            : 토큰의 타입, 해싱 알고리즘 정보를 포함
        2) 내용
            : 사용자 정보나 권한 같은 클레임(claim)을 포함
            - Base64로 인코딩, 암호화가 되지 않기 때문에 중요한 정보 저장은 권장 x
        3) 서명
            : header나 payload를 조합하여 주어진 비밀 키로 서명한 값


 */
