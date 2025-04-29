package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.StudentResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.StudentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.StudentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.entity.B_Student;
import com.example.korea_sleepTech_springboot.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service // 비즈니스 로직 처리 역할
public class StudentService {
    // 의존성 주입
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    // 메서드
    public List<StudentResponseDto> getAllStudents() {
        // 전체 학생 데이터 조회
        List<StudentResponseDto> studentResponseDtos = null;

        try {
            List<B_Student> students = studentRepository.findAll();

            // students를 studentDtos로 변환
            studentResponseDtos = students.stream() // stream API로 데이터 처리(전체 리스트 순회 + 각 요소에 동일 기능 적용)
                    .map(student -> new StudentResponseDto(
                            student.getId(),
                            student.getName()
                    ))
                    .collect(Collectors.toList());
            // 컬렉션 프레임워크 + stream API
            // 1) 컬렉션데이터.stream(): 스트림으로 변환
            // 2) .map(), .filter() 등 중간 연산
            // 3) 리스트 형태로 다시 변환 - .collect(Collectors.toList())

            return studentResponseDtos;

        }catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentResponseDto getStudentById(Long id) {
        StudentResponseDto studentResponseDto = null;

        try {

            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id));

            studentResponseDto = new StudentResponseDto(
                    student.getId(),
                    student.getName()
            );

            return studentResponseDto;

        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentResponseDto createStudent(StudentCreateRequestDto studentDto) {
        StudentResponseDto studentResponseDto = null;

        try {
            B_Student student = new B_Student(
                    studentDto.getName(),
                    studentDto.getEmail()
            );

            B_Student savedstudent = studentRepository.save(student);
            // 저장 되고 난 후의 savedstudent를
            studentResponseDto = new StudentResponseDto(
                    savedstudent.getId(),
                    savedstudent.getName()
            );

            return studentResponseDto;

        }catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentResponseDto updateStudent(Long id, StudentUpdateRequestDto studentDto) {
        StudentResponseDto responseDto = null;

        try{
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id)); // id에 맞는 학생이 없을 경우

            student.setName(studentDto.getName()); // 이름 수정

            // JPA save() 메서드의 동작 방식 (2가지)
            // 1) 새로운 객체(PK 없음) 저장: INSERT SQL 실행 (새로 저장)
            // 2) 기존 객체(PK 있음) 저장: UPDATE SQL 실행 (기존 데이터 수정)

            // cf) PK 값이 null 인지 유무를 자동 확인
            // >>> save() 하나로 JPA가 자동 저장을 처리
            B_Student updatedStudent = studentRepository.save(student); // UPDATE SQL 실행

            responseDto = new StudentResponseDto(
                    updatedStudent.getId(),
                    updatedStudent.getName()
            );

            return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public void deleteStudent(Long id) {

        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id));

            studentRepository.delete(student); // 조회한 학생 객체를 DB에서 삭제

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }
}
