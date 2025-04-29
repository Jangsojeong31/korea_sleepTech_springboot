package com.example.korea_sleepTech_springboot.dto.request;

import com.example.korea_sleepTech_springboot.entity.C_Category;
import lombok.Getter;
import lombok.Setter;

// 책 생성 시 클라이언트가 서버에게 전달되는 데이터
@Getter
@Setter
public class BookCreateRequestDto {
    private String writer;
    private String title;
    private String content;
    private C_Category category;
}
