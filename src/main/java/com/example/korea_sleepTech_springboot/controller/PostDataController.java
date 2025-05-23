package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.dto.file.PostRequestDto;
import com.example.korea_sleepTech_springboot.dto.file.PostResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.service.PostDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/post-datas")
public class PostDataController {
    private final PostDataService postDataService;

    // 1) 게시물 생성(등록) - 파일 업로드 기능 포함
    // 단일 파일 업로드 (consumes로 multipart 설정 필수)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<PostResponseDto>> createPost (
            @RequestPart("data") @Valid PostRequestDto dto,
            @RequestPart(value = "file", required = false)MultipartFile file
    ) {

    }

    // 2) 게시물 단건 조회

    // 3) 게시물 수정

    // 4) 게시물 삭제


}
