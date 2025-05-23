package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDataRepository extends JpaRepository<Post, Long> {
}
