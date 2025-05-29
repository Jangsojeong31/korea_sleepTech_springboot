package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

// 1 대 1 관계
// : 두 테이블이 1:1로 매핑
// ex) 사용자 한 명 당 하나의 건강 정보(개인정보)를 가짐
@Entity
public class UserExample {
    @Id @GeneratedValue
    private Long id;
    private String name;

    // 참조되는 테이블에서 mappedBy 옵션 사용
    // : 해당 테이블의 PK 값이 다른 테이블에서 참조됨(FK)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfileExample profile;

    // 일 대 다 관계
    // : JPA에서 1:N의 관계의 주인은 보통 "N"
    // >> mappedBy 주인 설정
    @OneToMany(mappedBy = "userExample", cascade = CascadeType.ALL)
    private List<PostExample> posts = new ArrayList<>();


}

/*
CREATE TABLE user_example (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);
 */
