package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.C_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<C_Book, Long> {
}
