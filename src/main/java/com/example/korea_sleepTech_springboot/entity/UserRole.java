package com.example.korea_sleepTech_springboot.entity;

// cf) user_roles 테이블 Entity
// : 별도로 생성하지 않아도 가능
// >> Spring JPA에서 다대다(@ManyToMany) 관계는 기본적으로 중간 테이블을 자동 관리

// +) user_role 테이블을 엔티티로 만들어야 하는 경우
// : 중간 테이블에 추가 정보가 존재하는 경우 (ex. 역할 부여 날짜(assigned_at), 역할의 상태(is_active), 권한 부여자 정보(assigned_by) 등)

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "user_roles")
@NoArgsConstructor
public class UserRole {
    // 복합키
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

//    private LocalDateTime assignedAt; 추가 정보 예시

    @Builder
    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
        this.id = new UserRoleId(user.getId(), role.getId());
    }

}
