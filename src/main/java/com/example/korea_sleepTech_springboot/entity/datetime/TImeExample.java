package com.example.korea_sleepTech_springboot.entity.datetime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TImeExample extends BaseTimeEntity {
//    private LocalDateTime createdAt; // BaseTimeEntity 내의 필드와 동일 -> 오류 발생
    @Id
    @GeneratedValue
    private Long id;

    // BaseTimeEntity의 필드를 상속받음
}
