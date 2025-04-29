package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class C_Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable 속성 = null 가능 여부, length = VARCHAR(N))
    // >> @Column 생략 시 기본값: nullable = true, length = 255
    @Column(nullable = false, length = 50) // 최대 50글자(이상 넘어가면 데이터가 자동으로 잘려서 저장 또는 오류 발생)
    private String writer;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    // JPA에서 열거형 데이터를 DB에 저장할 때는 문자열로 저장(권장)

    // cf) JPA + RDB(MySQL)에서는 DB ENUM 타입의 직접 사용을 권장하지 x
    // >>> DB에는 VARCHAR로 저장 + JPA에서 ENUM 매핑

    // cf) DB에서 데이터에 대한 검증 방법: CHECK 제약 조건을 사용 (IN 연산자)
    @Column(nullable = false)
    private C_Category category;
}
