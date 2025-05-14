package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 복합 키 설정 클래스
@Embeddable // 복합 키로 사용할 수 있도록 지정
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode // equals(), haseCode() 자동 구현, Serializable 구현할 때는 필수
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
