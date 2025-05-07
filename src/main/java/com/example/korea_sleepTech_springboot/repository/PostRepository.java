package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.D_Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<D_Post, Long> {
}
