package com.example.korea_sleepTech_springboot.dto.file;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
