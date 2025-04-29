package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.entity.C_Book;
import com.example.korea_sleepTech_springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookResponseDto createBook(BookCreateRequestDto requestDto) {

        BookResponseDto responseDto = null;
        try {
            C_Book newBook = new C_Book(null, requestDto.getWriter(), requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

            C_Book savedBook = bookRepository.save(newBook);

            responseDto = new BookResponseDto(
                    savedBook.getWriter(),
                    savedBook.getTitle(),
                    savedBook.getCategory()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
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
}
