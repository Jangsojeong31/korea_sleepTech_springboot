package com.example.korea_sleepTech_springboot.dto.file;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<FileResponseDto> files;
}
