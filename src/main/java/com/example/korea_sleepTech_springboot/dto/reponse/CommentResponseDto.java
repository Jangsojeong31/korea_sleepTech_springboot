package com.example.korea_sleepTech_springboot.dto.reponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private String content;
    private String commenter;
}
