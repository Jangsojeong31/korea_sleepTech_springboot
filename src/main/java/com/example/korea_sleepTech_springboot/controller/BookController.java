package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.entity.C_Category;
import com.example.korea_sleepTech_springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
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
    public ResponseEntity<ResponseDto<BookResponseDto>> createBook(@RequestBody BookCreateRequestDto dto) {
        try {
            ResponseDto<BookResponseDto> book = bookService.createBook(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 2) READ - 전체 책 조회
    @PreAuthorize("hasRole('USER')") // 경로 진입 전 권한 확인
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

    // 2. 검색 & 필터링 (@RequestParam)
    // : GET 메서드

    // 1) 제목에 특정 단어가 포함된 책 조회
    @GetMapping("/search/title")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByTitleContaining(
            @RequestParam String keyword // 매개변수
            // 경로값에 ? 이후의 데이터를 키-값의 쌍으로 추출되는 값 (?키=값)
            // >> 키의 변수에 값이 할당
            // >> localhost:8080/books/search/title?keyword=xxx

            // +) RequestParam은 항상 문자열로 반환
            // >> 숫자형은 int와 Long으로 자동 변환
            // - 변환 실패 시 400 에러가 발생 -> catch 구문
    ) {
        try {
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByTitleContaining(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 2) 카테고리별 책 조회
    @GetMapping("/search/{category}")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByCategory(
            @PathVariable C_Category category) {
        try {
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // cf) '제목으로 검색'과 '카테고리별 조회'의 차이
    // 제목 검색) Query Parameter 사용
    //          >> GET /books/search/title?keyword=x
    // : "제목에 특정 단어가 포함되어 있는 책들"을 검색
    // - 정확한 자원을 가리키는 것이 아니라 필터 조건을 제시하는 형태
    // 카테고리별 조회) Path Variable 사용
    //              >> GET /books/category/NOVEL
    //  : Category 값이 어떤 고정된 그룹(NOVEL, ESSAY, POEM 등) 중 하나
    // - "A라는 카테고리에 속한 책 리스트"라는 명확한 리소스를 가리키는 URI로 간주

    // 3) 카테고리와 작성자별 책 조회
    // : 복합 조건 검색은 보통 Query Parameter를 조합해서 처리
    @GetMapping("/search/category-writer")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByCategoryAndWriter(
            @RequestParam(required = false) C_Category category,
            @RequestParam String writer

            // required = false: 해당 매개변수는 필수값 x
            // - GET /books/search/category-writer?category=xxx&writer=yyy
            // - GET /books/search/category-writer?writer=yyy
    ) {
        try {
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByCategoryAndWriter(category, writer);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
