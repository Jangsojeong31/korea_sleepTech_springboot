package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
