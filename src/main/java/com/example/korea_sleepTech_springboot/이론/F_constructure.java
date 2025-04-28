package com.example.korea_sleepTech_springboot.이론;

public class F_constructure {
    // 스프링 부트의 구조
    // 클라이언트(요청) >> Controller >> Service >> Repository >> DB

    // 1) Presentation(프레젠테이션) 계층
    // : controller(컨트롤러)
    //  - HTTP 요쳥을 받고 해당 요청에 따라 이후 동작을 제어(알맞은 비즈니스 계층으로 전송)

    // 2) Business(비즈니스) 계층
    // : service
    //  - 모든 비즈니스 로직(서비스를 만들기 위한 로직, 웹 사이트의 모든 작업을 담당)을 처리

    // 3) Persistence 계층
    // : repository
    //  - 모든 DB 관련 로직 처리 (DB에 접근하는 객체 사용 가능)


}
