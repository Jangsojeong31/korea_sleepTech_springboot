package com.example.korea_sleepTech_springboot.entity.datetime;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
@PrePersist
    : Entity가 처음 저장될 때 실행
    - 보통 created_at 필드에 사용

@PreUpdate
    : Entity가 수정될 때마다 실행
    - 보통 updated_at 필드에 사용
 */

// JPA에서 공통된 엔티티 속성(예: 생성일, 수정일 등)을 자동으로 처리할 때 사용하는 어노테이션
@MappedSuperclass
    // 해당 어노테이션이 붙은 클래스는 JPA 엔티티의 부모 클래스로 사용
    // : 자식 클래스에서 공통 필드만 상속받게 해줌
@EntityListeners(AuditingEntityListener.class)
    // JPA가 엔티티의 생성일과 수정일을 자동으로 기록하게 도와주는 감시자(Auditing 리스너)를 등록
    // @CreatedDate, @LastModifiedDate 같은 어노테이션을 사용하기 위해 반드시 등록
    // >> 자동으로 시간 기록해주는 기능을 활성화
    // >> Spring Boot 설정 클래스에 Auditing 기능 활성화
public abstract class BaseTimeEntity {
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    // 기존 방식
//    @PrePersist
//    public void prePersist() {
//        LocalDateTime now = LocalDateTime.now();
//        this.createdAt = now;
//        this.updatedAt = now;
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }

}
