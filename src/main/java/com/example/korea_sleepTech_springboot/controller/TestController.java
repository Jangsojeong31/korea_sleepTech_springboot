package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.entity.A_TestEntity;
import com.example.korea_sleepTech_springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI vs URL
// URI (Uniform Resource Identifier)
// : 자원을 식별하는 데 사용되는 문자열
// - 웹 페이지, 이미지, 파일, 서비스 '엔드 포인트' 등

// URL (Uniform Resource Locator)
// : 자원의 위치를 나타내는 문자열, 웹 주소를 의미 (http://localhost:8080)
// +) 컴퓨터 네트워크 상의 지원
// >> 식별자 + 위치

// cf) URI가 더 포괄적 개념

//@Controller // : 웹 요청을 처리하는 클래스임을 명시 (반환되는 데이터 타입이 유연 ex. JSP, Thymeleaf 등)
@RestController // = @Controller + @ResponseBody : 데이터를 반환할 때 ex. JSON 등
@RequestMapping("/test") // 특정 URI로 요청이 올 때, 특정 메서드와 매핑하기 위해 사용
public class TestController {
    // 요청을 받고 나면 해당 요청의 로직 수행을 비즈니스 로직이 담당 (Service)
    @Autowired // : 필드 주입 방식
    TestService testService;

    @GetMapping
    public List<A_TestEntity> getAllTest() {
        List<A_TestEntity> tests = testService.getAllTests();
        return tests;
    }

    @GetMapping("/{id}")
    public A_TestEntity getTestById(@PathVariable Long id) { // @PathVariable: URL 경로에서 변수값을 포함하여 전달 (경로 변수를 표시)
        A_TestEntity test = testService.getTestById(id);
        return test;
    }

    @PostMapping
    public A_TestEntity createTest(@RequestBody A_TestEntity ATestEntity) {
        A_TestEntity test = testService.createTest(ATestEntity);
        return test;
    }

    @PutMapping
    public A_TestEntity updateTest(@PathVariable Long id, @RequestBody A_TestEntity ATestEntity) {
        return null;
    }

    @DeleteMapping
    public void deleteTest(@PathVariable Long id) {

    }

}
