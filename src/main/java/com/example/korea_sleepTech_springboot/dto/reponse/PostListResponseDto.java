package com.example.korea_sleepTech_springboot.dto.reponse;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class PostListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
}
