package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA에서만 사용 가능하도록 (기본생성자를 protected로 생성)
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true) // name = "role_name": 생략가능
    private String roleName;

    @Builder
    public Role(String roleName){
        this.roleName = roleName;
    }
}
