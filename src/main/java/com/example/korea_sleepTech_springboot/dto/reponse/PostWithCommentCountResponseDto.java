package com.example.korea_sleepTech_springboot.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostWithCommentCountResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int commentCount;
}
