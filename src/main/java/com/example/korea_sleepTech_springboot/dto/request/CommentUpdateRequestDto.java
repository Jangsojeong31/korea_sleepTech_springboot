package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentUpdateRequestDto {
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;
}
