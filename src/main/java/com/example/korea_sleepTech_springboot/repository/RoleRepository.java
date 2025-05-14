package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String user);
}
