package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.*;

@Entity
public class UserProfileExample {
    @Id
    @GeneratedValue
    private Long id;

    private String userImage;

    // 참조하는 테이블
    // name 옵션은 FK로 참조하는 필드
    // : UserExample의 PK값이 user_id값으로 참조됨(FK)
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserExample user;
}

/*
CREATE TABLE user_profile_example (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bio VARCHAR(255),
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user_example(id)

 */