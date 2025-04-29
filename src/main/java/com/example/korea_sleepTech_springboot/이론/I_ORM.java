package com.example.korea_sleepTech_springboot.이론;

public class I_ORM {
    // === ORM(Object-Relational Mapping) ===
    // : 객체와 관계형 DB 간의 데이터를 1:1로 매핑해주는 기술
    // >> DB의 테이블과 애플리케이션의 객체 사이의 구조적 불일치를 해결하는 솔루션

    // cf) RDBMS(MySQL): user_password (lower_snake_case) - 테이블
    // cf) SpringBoot(Java): userPassword (lowerCamelCase) - 클래스

    // 1. ORM의 특징
    // : 객체와 테이블이 1:1 매핑
    // : 객체지향적인 데이터 조작 (SQL 대신 자바 객체 메서드로 CRUD 작업 수행)
    // - 반복적인 SQL 작성 없이 데이터 조작 가능 (DBMS에 독립적)
    // 단점: 학습 곡선 존재, 복잡한 쿼리 작성 어려움

    // 2. ORM 동작 원리
    // : 각 테이블은 클래스에, 테이블의 각 행(Row)은 객체에 매핑
    // - Entity: DB의 테이블과 매핑되는 클래스 (@Entity)
    // - Field: DB의 열(Column)에 해당하는 필드

    // 3. JPA(Java Persistence API)
    // : ORM 중 하나
    // - Java 애플리케이션에서 관계형 DB를 쉽게 다룰 수 있는 ORM 표준 기술

    // cf) 영속성 컨텍스트
    // : 엔티티(Entity)의 생명 주기를 관리하는 공간
    // - DB 와의 연결은 유지하면서 엔티티 객체들을 관리

    /*
    고객 데이터 관리
    === RDBMS(MySQL) ===

    create table customer(
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(50),
     email VARCHAR(100)
    );

    === SPRING BOOT(JAVA) ===
    @Entity
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String email;
    }

     */

    // cf) ORM에서 사용되는 주요 애너테이션
    // 1. @Entity
    // : 클래스를 DB 테이블과 매핑할 때 사용
    // +) name 옵션 추가
    //      : 엔티티 이름을 지정, 테이블명과 클래스명이 다를 경우 명시
    //      @Entity(name="userTable") // 테이블명은 user_table
    //      public class user {}

    // 2. @Id
    // : 해당 필드를 DB 테이블의 '기본키(PK)'로 지정
    // +) 옵션 없이 사용 가능하나, 다른 애너테이션과 함께 기본키 생성 방식이나 타입 지정 가능
    //  >> 주로 GeneratedValue와 함께 사용(기본키 자동 생성 전략 설정 - strategy 옵션 / GenerationType.IDENTITY(AUTO_INCREMENT))

    // 3. @Column
    // : 필드를 특정 테이블의 열과 매핑
    // - 생략 시 기본으로 필드 이름이 열 이름으로 사용
    // +) 옵션
    //      - name 옵션: 열 이름 지정
    //      - nullable 옵션: 열이 null 값을 허용할지 여부를 설정 (기본값: true)
    //      - length 옵션: String 타입의 열 길이를 지정 (기본값 255)
    //      - unique 옵션: 해당 필드의 값이 유일해야 하는지 여부를 지정 (기본값: false)
    //      >> 각 옵션은 콤마(,)로 구분하여 나열

    // 4. @Table
    // : 클래스가 어떤 테이블과 매핑되는지 명시
    // : 생략 시 기본으로 클래스 이름이 테이블 명으로 사용
    // +) name 옵션

    // cf) JPA(ORM, 객체와 RDBMS의 연결) vs MyBatis(SQL Mapper, SQL 중심 접근)

    // === 환경 설정 ===
    // 1) build.gradle
    // : 필요한 의존성: Spring Data JPA, MySQL Driver

    // 2) application.properties
    // : 애플리케이션 설정(DB 서버, 사용자 정보, 비밀번호 설정)


}
