package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Set<Student> students = new HashSet<>();
}
/*
    CREATE TABLE students (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(100)
    );
     */
