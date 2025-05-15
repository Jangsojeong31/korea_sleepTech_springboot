package com.example.korea_sleepTech_springboot.exception;

import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice // 모든 @RestController 에서 발생하는 예외를 처리
/*
1. 단일 책임 원칙(SRP)
    : 예외 처리를 Controller가 아닌 Global Exception Handler에서 담당

2. 재사용성 증가
    : 모든 Controller에 대한 예외 처리가 한 곳에서 관리

3. 가독성 향상
 */
public class GlobalExceptionHandler {
    // 400 - 잘못된 요청 (Bad Request)
    // : 잘못된 인자가 전달되거나(IllegalArgumentException) DTO 검증 실패 시(MethodArgumentNotValidException)
    // cf) @Valid 애너테이션으로 오류 발생 시 MethodArgumentNotValidException
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class, IllegalStateException.class})
    public ResponseEntity<ResponseDto<?>> handleBadRequest(Exception e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Bad Request: " + e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // 403 - 권한 없음
    // : 인증되지 않은 사용자가 접근하려고 할 때
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto<?>> handleAccessDenied(AccessDeniedException e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Access Denied: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // 404 - (요청)엔티티를 찾을 수 없는 경우
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Not Found: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 409 - 데이터 충돌 (무결성 위반)
    // : DB 제약 조건 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Conflict: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 500 - 서버 내부 오류
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Internal Server Error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
