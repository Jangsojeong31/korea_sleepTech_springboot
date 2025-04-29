package com.example.korea_sleepTech_springboot.이론;

public class H_DispatcherServlet {
    // === DispatcherServlet (디스패처 서블릿) ===
    // cf) dispatch
    //      : 보내다
    // : 스프링 부트의 핵심 서블릿
    // - 클라이언트의 요청을 받아 "알맞은 핸들러(Controller)"를 찾아 실행하고 처리된 결과를 다시 클라이언트에게 반환
    // >> 웹 애플리케이션의 요청 흐름을 관리하는 중심 역할 (HttpServletRequest, HttpServletResponse)

    // DispatcherServlet의 동작 과정
    // 1) 클라이언트 요청 처리
    // : 사용자가 웹에서 요청 (GET, POST, PUT, DELETE)을 보냄
    // - 서블릿 컨테이너가 DS로 요청 전달

    // 2) Handler(Controller) 조회
    // : Handler Mapping을 통해 요청 URI에 맞는 '핸들러'를 찾음
    // ex) /student로 요청 >> StudentController가 매핑

    // 3) Handler Adapter 조회
    // : 매핑한 핸들러 실행을 위한 어댑터를 찾고 Controller가 실행될 수 있는 형태로 어댑터가 변환

    // 4) Handler(Controller) 실행
    // : 어댑터가 찾은 핸들러 실행
    // - 요청 처리와 결과를 어댑터로 반환




}
