package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_change_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RoleChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String email;

    @Lob // TEXT 타입 명시
    private String prevRoles;
    @Lob
    private String newRoles;

    private String changedBy;
    private String changedType;
    private String changeReason;

    @Column(updatable = false) // 변경날짜는 바뀌면 안됨
    private LocalDateTime changedAt;

    @PrePersist // Entity가 영속화되기 전에 실행 - 엔티티의 상태가 'DB에 저장되기 전에 동작이 수행'되도록 지정
    public void prePersist() {
        this.changedAt = LocalDateTime.now(); // 등록 시점을 자동 기록
    }

}
