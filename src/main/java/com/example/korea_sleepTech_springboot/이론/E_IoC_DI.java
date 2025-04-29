package com.example.korea_sleepTech_springboot.이론;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class E_IoC_DI {
    /*
    제어의 역전(IoC) & 의존성 주입(DI)

     */
    // 1. 전통적인 자바 프로그래밍 방식
    class Book1 {
        private String title;
        public Book1(String title) {
            this.title = title;
        }
        public String getTitle() {
            return this.title;
        }
    }

    class BookStore1 {
        private Book1 book;
        public BookStore1() {
            // Book 객체를 생성자가 직접 생성
            // : 개발자가 객체 생성과 관리(수명 주기)를 모두 책임

            // +) Book1이 변경되면 BookStore1도 같이 수정
            // : 결합도가 높다.
            // >> 확장성, 유지보수성, 테스트에 어려움
            this.book = new Book1("Spring Boot 기초");
        }
        public void displayBook() {
            System.out.println("Book : " + book.getTitle());
        }
    }

    // 2. 스프링 제어의 역전 프로그래밍 방식
    @Component
    // : 스프링 컨테이너가 해당 객체를 관리하도록 설정
    // cf) 스프링 빈(Bean)
        // : 스프링 컨테이너에 의해 관리되는 재사용 가능한 소프트웨어 컴포넌트
    class Book2 {
        private String title;

        public Book2 () {
            this.title = "스프링 기초";
        }

        public String getTitle() {
            return this.title;

        }

    }

    @Component // 해당 클래스를 스프링 빈으로 등록하는 어노테이션
    class BookStore2 {
        private Book2 book;

        // 스프링이 Book2 객체를 생성해서 자동으로 BookStore2에 넣어줌 (매개변수로 전달)
        // : 개발자가 직접 new Book2()를 하지 않고
        //    , 스프링 컨테이너가 객체를 스스로 만들어서 '주입'

        @Autowired // 스프링 컨테이너에서 해당 타입의 빈을 칮아 주입하는 어노테이션
        public BookStore2(Book2 book) {
            this.book = book;
        }
        public void displayBook() {
            System.out.println("Book2 : " + book.getTitle());
        }
    }

    // 1. 제어의 역전
    // : 프로그램의 제어 흐름을 개발자가 아닌 '외부 컨테이너(스프링)'에 위임하는 방식
    // - 제어의 권한이 컨테이너에게 있어 객체의 생명 주기를 컨테이너가 관리

    // 2. 의존성 주입
    // : 클래스가 필요로 하는 객체(의존성)를 외부에서 주입해주는 방식
    // - 제어의 역전을 구현하기 위한 방법
    // - 객체 간의 의존성을 낮추고, 유연성과 재사용성을 높임
    // 2-1. 주입의 종류 : 생성자 주입(권장), 필드 주입, 세터 주입 등의 방식으로 이루어짐

}

/*
1. 스프링 컨테이너
: 애플리케이션 내에서 객체(빈, Bean)의 생명 주기와 설정을 관리
- 제어의 역전(IoC)을 실현, 의존성 주입(DI)를 지원

2. 빈
: 스프링 컨테이너가 관리하는 객체
- 개발자에 의해 명시적으로 정의되거나, 스프링에 의한 자동으로 감지되어 관리
- 서비스, 리포지토리, 컨트롤러 등의 역할을 수행하는 컴포넌트로 사용

cf) 빈의 경우 클래스명의 첫글자를 소문자로 바꾸어 관리 (Book, BookStore 클래스 >> book, bookStore 빈)

+) 빈 선언 방법
: @Component, @Bean 어노테이션 사용
+ @Service, @Repository 등 내부에 @Component가 포함되어 있음


 */
