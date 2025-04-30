package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.C_Book;
import com.example.korea_sleepTech_springboot.entity.C_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<C_Book, Long> {
    // 사용자 쿼리 메서드
    // : 인터페이스의 추상 메서드 구조를 가짐
    // : 반환타입 메서드명(매개변수,,,,);

    List<C_Book> findByTitleContaining(String keyword);
    // >>> SQL문 변환) SELECT * FROM book WHERE title LIKE %keyword; (LIKE %keyword: keyword가 포함)

    List<C_Book> findByCategory(C_Category category);
    // >> SQL문 변환) SELECT * FROM book WHERE category=category;

    List<C_Book> findByWriter(String writer);
    // >> SQL문 변환) SELECT * FROM book WHERE writer=writer;

    List<C_Book> findByCategoryAndWriter(C_Category category, String writer);
    // >> SQL문 변환) SELECT * FROM book WHERE category=category and writer=writer;
}
