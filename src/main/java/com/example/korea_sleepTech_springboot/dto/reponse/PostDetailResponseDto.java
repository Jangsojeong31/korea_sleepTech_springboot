package com.example.korea_sleepTech_springboot.dto.reponse;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private List<CommentResponseDto> comments;
}
