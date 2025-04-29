package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.B_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<B_Student, Long> {
}
