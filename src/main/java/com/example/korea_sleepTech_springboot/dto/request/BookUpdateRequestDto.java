package com.example.korea_sleepTech_springboot.dto.request;

import com.example.korea_sleepTech_springboot.entity.C_Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequestDto {
    // 수정 가능한 책의 데이터: 내용, 카테고리
    private String content;
    private C_Category category;
}
