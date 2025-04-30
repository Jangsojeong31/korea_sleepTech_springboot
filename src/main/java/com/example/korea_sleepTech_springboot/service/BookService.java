package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.entity.C_Book;
import com.example.korea_sleepTech_springboot.entity.C_Category;
import com.example.korea_sleepTech_springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public ResponseDto<BookResponseDto> createBook(BookCreateRequestDto requestDto) {

        try {
            C_Book newBook = new C_Book(null, requestDto.getWriter(), requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

            C_Book savedBook = bookRepository.save(newBook);

            BookResponseDto response = new BookResponseDto(
                    savedBook.getWriter(),
                    savedBook.getTitle(),
                    savedBook.getCategory()
            );

            return ResponseDto.setSuccess("새로운 책이 정상적으로 등록되었습니다.", response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("책 등록 중 오류가 발생하였습니다." + e.getMessage());
        }
    }

    public List<BookResponseDto> getAllBooks() {
        List<BookResponseDto> responseDtos = null;
        try {
            List<C_Book> books = bookRepository.findAll();

            responseDtos = books.stream()
                    .map(book -> new BookResponseDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

        }catch(Exception e) {
            e.printStackTrace();
        }

        return responseDtos;
    }

    public BookResponseDto getBookById(Long id) {
        BookResponseDto responseDto = null;
        try {
            C_Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다."));

            responseDto = new BookResponseDto(
                    book.getWriter(),
                    book.getTitle(),
                    book.getCategory()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public BookResponseDto updateBook(Long id, BookUpdateRequestDto dto) {
        BookResponseDto responseDto = null;
        try {
            C_Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다."));

            // 수정사항 수정하기
            book.setContent(dto.getContent());
            book.setCategory(dto.getCategory());

            // 수정한 책 저장
            C_Book updatedBook = bookRepository.save(book);

            responseDto = new BookResponseDto(
                    updatedBook.getWriter(),
                    updatedBook.getTitle(),
                    updatedBook.getCategory()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public void deleteBook(Long id) {
        try {
            C_Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 책을 찾을 수 없습니다: " + id));

            bookRepository.delete(book);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseDto<List<BookResponseDto>> getBooksByTitleContaining(String keyword) {
        List<BookResponseDto> books = null;

        try {
            List<C_Book> booksByTitleContaining = bookRepository.findByTitleContaining(keyword);

            books = booksByTitleContaining.stream()
                    .map(book -> new BookResponseDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("책 검색을 정상적으로 완료하였습니다.", books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("책을 검색하는 동안 문제가 발생하였습니다.");
        }
    }

    public ResponseDto<List<BookResponseDto>> getBooksByCategory(C_Category category) {
        List<BookResponseDto> books = null;

        try {
            List<C_Book> booksByCategory = bookRepository.findByCategory(category);

            books = booksByCategory.stream()
                    .map(book -> new BookResponseDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("카테고리별 책 조회가 성공하였습니다.", books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("카테고리별 책을 검색하는 동안 문제가 발생하였습니다.");
        }
    }

    public ResponseDto<List<BookResponseDto>> getBooksByCategoryAndWriter(C_Category category, String writer) {
        List<BookResponseDto> books = null;

        List<C_Book> searchingBook = null;

        try {
            if (category == null) { // writer로만 검색
                searchingBook = bookRepository.findByWriter(writer);
            } else { // category와 writer로 검색
                searchingBook = bookRepository.findByCategoryAndWriter(category, writer);
            }

            books = searchingBook.stream()
                    .map(book -> new BookResponseDto(
                            book.getWriter(),
                            book.getTitle(),
                            book.getCategory()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("조회가 완료되었습니다.", books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("카테고리와 작성자로 조회하는 동안 문제가 발생하였습니다.");
        }
    }
}
