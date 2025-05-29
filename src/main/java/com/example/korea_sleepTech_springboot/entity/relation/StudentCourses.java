package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class StudentCourses {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrollmentDate;
    private int grade;
}

// 연결(중간) 테이블에 컬럼이 추가되는 경우
/*
CREATE TABLE student_courses (
    student_id BIGINT,
    course_id BIGINT,

    enrollment_date DATETIME,
    grade INT,

    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
 */
