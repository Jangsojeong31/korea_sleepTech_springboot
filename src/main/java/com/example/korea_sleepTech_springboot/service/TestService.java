package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.entity.A_TestEntity;
import com.example.korea_sleepTech_springboot.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    // TestService 실행(인스턴스화)되면 자동으로 testRepository에 의존성이 주입되기 때문에
    // : final로 반드시 객체 초기화
    private final TestRepository testRepository;

    // 생성자 주입 방식
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<A_TestEntity> getAllTests() {
        return testRepository.findAll(); // 전체 test 목록을 가져오기
    }

    public A_TestEntity createTest(A_TestEntity ATestEntity) {
        return testRepository.save(ATestEntity);
    }

    public A_TestEntity getTestById(Long id) {
        Optional<A_TestEntity> optionalTestEntity = testRepository.findById(id);

        A_TestEntity test = optionalTestEntity.orElseThrow(() ->
                new RuntimeException("해당 ID를 가진 데이터가 없습니다: " + id));
        return test;
    }
}
