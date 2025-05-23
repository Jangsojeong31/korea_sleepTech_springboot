package com.example.korea_sleepTech_springboot.dto.file;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {
    private String originalName;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
}
