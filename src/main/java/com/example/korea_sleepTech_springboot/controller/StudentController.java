package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.dto.reponse.StudentResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.StudentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.StudentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody: Restful 웹 서비스의 컨트롤러임을 명시
@RequestMapping("/student") // 해당 컨트롤러의 모든 요청 URL이 "/student"로 시작함을 정의
public class StudentController {
    // 비즈니스 로직을 처리하는 서비스 객체를 주입받아 사용
    // 의존성 주입 방식: 생성자 주입(권장), 필드 주입, setter 주입
    private final StudentService studentService;

    // 생성자 주입 방식
    public StudentController(StudentService studentService){ // 매개변수로 StudentService 객체를 주입함
        this.studentService = studentService;
    }

    // 1) 전체 학생 목록 조회 (GET)
    /*
    @Params(요청-매개변수): x
    @Return(응답-반환값): List<StudentDto>
     */
    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 2) 특정 ID로 학생 조회 (GET)
     /*
    @Params(요청-매개변수): id
    @Return(응답-반환값): StudentDto
     */
    // "/student/{id}" 경로로 오는 GET 요청을 처리 - {id}는 경로 변수로 사용
    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id){
        //@PathVariable: URL 경로에 전달된 동적 데이터를 메서드 파라미터로 매핑(매개변수로 전달함)
        return studentService.getStudentById(id);
    }

    // 3) 새로운 학생 등록 (POST)
     /*
    @Params(요청-매개변수): B_Student
    @Return(응답-반환값): StudentDto
     */
    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentCreateRequestDto student) {
        // @RequestBody: 클라이언트에서 전달된 JSON 데이터를 B_Student 형태로 변환
        return studentService.createStudent(student);
    }

    // 4) 특정 ID의 학생 정보 수정 (PUT)
     /*
    @Params(요청-매개변수): 수정할 학생 id, 수정할 학생 정보 StudentDto(email은 고유값이기 떄문에 변경 불가)
    @Return(응답-반환값): StudentDto
     */
    @PutMapping("/{id}")
    public StudentResponseDto updateStudent (@PathVariable Long id, @RequestBody StudentUpdateRequestDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    // 5) 특정 ID의 학생 정보 삭제 (DELETE)
     /*
    @Params(요청-매개변수): 삭제할 학생 id
    @Return(응답-반환값): ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    // ResponseEntity: HTTP 응답 전체(= 상태코드, 헤더, 본문)를 직접 제어해서 클라이언트에게 보내는 객체
    // - 데이터를 보다 안전하게 포장해서 전달

}
