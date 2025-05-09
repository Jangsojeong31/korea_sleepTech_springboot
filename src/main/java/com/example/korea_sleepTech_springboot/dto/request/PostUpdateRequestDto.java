package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
/*
== DTO ==
: DTO는 생성 시점에 값이 채워짐 (불변성 유지)
    >> 필드는 주로 final 설정

cf) 요청 DTO의 경우 일반적으로 생성되고 난 후 변경 x (불변성)
        >> @Setter가 불필요
*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateRequestDto {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

}
