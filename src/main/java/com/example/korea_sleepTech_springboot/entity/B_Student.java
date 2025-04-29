package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity // DB 테이블과 1:1 매핑, JPA 엔티티임을 선언(반드시 PK가 필요)
@Table(name = "student")
//@Table(
//        name = "student",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "name", "email")  // 복합키와 유사한 개념 but null은 가능
//        }
//)
@Getter
@Setter
@NoArgsConstructor
public class B_Student {
    @Id // 해당 필드가 테이블의 기본키임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 값을 자동 생성
    private Long id;

    // final 또는 @NonNull 사용 시 @RequiredConstructor에서 매개변수로 지정
    private String name;

    // UNIQUE 제약 조건 명시
    // 1) 컬럼 단위 명시
    @Column(unique = true) // JPA가 테이블을 만들 때 email 컬럼에 UNIQUE 제약을 자동 설정
    // 2) @Table 에서 제약 조건 설정(위에서 확인)
    private String email;

//    protected B_Student() {}
    // JPA는 엔티티 생성 시 기본 생성자를 사용 => 필수! (여기서는 @NoArgsConstructor로 대신)

    public B_Student(String name, String email){
        this.name = name;
        this.email = email;
    }
}
