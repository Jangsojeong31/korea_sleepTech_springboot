package com.example.korea_sleepTech_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication : 스프링부트에 필요한 기본 설정 제공
@EnableJpaAuditing // Auditing 기능 활성화
@SpringBootApplication
public class KoreaSleepTechSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoreaSleepTechSpringbootApplication.class, args);
	}
	// 인자
	// 1) KoreaSleepTechSpringbootApplication.class
	// : 스프링부트3 애플리케이션의 메인 클래스로 사용할 클래스 정의
	// 2) args
	// : 커맨드 라인의 인수들을 전달
}
