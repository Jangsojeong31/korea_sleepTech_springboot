package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequestDto {
    @NotBlank(message = "제목은 필수 입력 값입니다.") // 입력 받을 때 유효성 검증
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;
    @NotBlank(message = "작성자는 필수 입력 값입니다.")
    private String author;
}
