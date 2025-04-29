package com.example.korea_sleepTech_springboot.entity;
// RDMBS의 test 테이블과 매핑될 클래스 (클래스명과 테이블명이 일치하지 않음)

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 테이블의 영속성을 위해서는 PK 값이 반드시 존재
@Table(name="test") // 클래스명과 테이블명이 다를 경우 name 속성으로 연결할 테이블명을 반드시 명시
@NoArgsConstructor
@Getter
@Setter
public class A_TestEntity {
    @Id // PK 설정 애너테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT에 맞춰서 자동 증가 전략 사용
    @Column(name = "id", updatable = false) // 생략 가능, updatable 속성: 수정 가능성 여부 명시
    private Long id;
    @Column(name = "name", nullable = false) // nullable 속성: 비워질 수 있는지 여부 명시
    private String name;
}
