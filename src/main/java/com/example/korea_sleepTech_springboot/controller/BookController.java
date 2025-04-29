package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK_API)
@RequiredArgsConstructor // final 필드를 매개변수로 가지는 생성자 생성 (+ 의존성 주입의 역할)
public class BookController {
    // Service 객체를 주입받아 저장하는 변수
    private final BookService bookService; // final 키워드 : 초기화 필수 -> @RequiredArgConstructor로 생성자 생성

    // 1. 기본 CRUD
    // 1) CREATE - BOOK 생성
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookCreateRequestDto dto) {
        BookResponseDto book = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // 2) READ - 전체 책 조회
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    // 3) READ - 단건 책 조회 ( 특정 ID )
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 4) UPDATE - 책 수정 ( 특정 ID )
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequestDto dto){
        BookResponseDto book = bookService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 5) DELETE - 책 삭제 ( 특정 ID )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
