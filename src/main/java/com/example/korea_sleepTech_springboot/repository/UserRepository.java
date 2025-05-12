package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 해당 엔티티에 전달하는 email이 존재하는 경우 true, 그렇지 않을 경우 false 반환
    boolean existsByEmail(String email);

    // User 타입의 엔티티를 검색 (email 기준)
    // : 존재하면 해당 객체 반환, 그렇지 않을 경우 빈 Optional 객체 반환
    Optional<User> findByEmail(String email);
}
