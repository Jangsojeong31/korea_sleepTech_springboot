package com.example.korea_sleepTech_springboot.이론;

public class L_Bean {
    /*
    === @Bean vs @Component ===
    1. 공통점
        : 스프링 빈(Bean)을 등록하는데 사용

    2. 차이점
        1) @Bean
            - 메서드 위에 붙임
            - 개발자가 메서드로 직접 등록 (수동 등록)
            : 외부 라이브러리, 복잡한 객체 등록 시 사용
            cf) @Configuration (설정용 클래스) 내부에서 사용

        2) @Component
            - 클래스 위에 붙임
            - 스프링이 직접 클래스를 찾아서 등록 (자동 등록)
            : 개발자가 직접 만든 클래스를 등록 시 사용
            cf) @Service, @Repository, @Controller 모두 컴포넌트 기반

    === 애너테이션의 전체 흐름 ===
    @Configuration: 스프링 설정 클래스임을 명시 - 스프링 컨테이너에 의해 관리 (내부에 @Bean 포함 가능)

    @ComponentScan: 지정된 패키지에서 @Component( + @Service, @Repository 등)의 애너테이션이 붙은 클래스를 자동으로 스캔
        - 스프링 빈으로 등록

    @Autowired (의존성 주입)
        : 스프링 컨테이너 내부의 빈을 자동으로 주입
        - 주로 생성자, 필드, 메서드에서 사용
     */
}
