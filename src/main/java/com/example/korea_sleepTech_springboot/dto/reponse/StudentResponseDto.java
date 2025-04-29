package com.example.korea_sleepTech_springboot.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDto {
    private Long id;
    private String name;
    // 데이터 전송 시 email 필드값은 엔티티에서 제외

}
