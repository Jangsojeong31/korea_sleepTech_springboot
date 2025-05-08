package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.D_Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<D_Comment, Long> {

}
