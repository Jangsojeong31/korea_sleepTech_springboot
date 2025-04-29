package com.example.korea_sleepTech_springboot.이론;

public class C_Servlet_SpringBoot {
    /*
    1. 웹 서블릿(Servlet)에서 HTTP 통신

    서블릿: 자바로 만든 웹 프로그램
        - 클라이언트의 요청에 따라 동적으로 서버를 처리하는 자바 클래스
        - 웹 서버에 배치,
        - HTTP 프로토콜을 통해 클라이언트 요청을 받고,
            자바 코드를 통해 웹 페이지 생성 | 데이터 처리 후 응답 반환

        +) Java EE(Enterprise Edition) 플랫폼 일부
            , 대부분 자바 웹 프레임워크들이 Servlet 기술을 기반으로 동작

        1) 톰캣 서버가 HTTP 요청을 받음
        2) 요청을 HttpServletRequest 객체로 감싸서 서블릿에게 전달
        3) 서블릿이 요청을 분석하고 처리
            ... 자바 내부에서 데이터 처리 ...
        4) 처리한 결과를 HttpServletResponse 객체에 담아 응답

    2. 스프링부트(SpringBoot)에서 HTTP 통신

        스프링부트
            : 서블릿을 쉽게 작동하게 해주는 프레임워크
            - Controller 라는 클래스를 만들어서 HTTP 요청을 쉽게 처리

            1) HTTP 요청 >> 스프링부트가 자동으로 컨트롤러 메서드로 연결
            2) 메서드의 '요청 파라미터'를 받아 처리
                ... 자바 내부에서 데이터 처리 ...
            3) 결과를 반환 >> 스프링부트가 자동으로 HTTP 응답으로 만들어 보냄

            cf) 서블릿에서는 직접 request와 response를 조작
                스프링부트에서는 @RequestParam, @GetMapping 등의 애너테이션을 사용하여 간단한 HTTP 통신 처리

     */
}
