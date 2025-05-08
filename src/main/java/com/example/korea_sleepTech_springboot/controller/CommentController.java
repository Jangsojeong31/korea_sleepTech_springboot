package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.COMMENT_API)
@RequiredArgsConstructor
public class CommentController {
    // 댓글
    // : CRUD 중 CUD만 처리 (조회는 Post 조회 시 댓글도 조회됨)

    private final CommentService commentService;

    // 1) 댓글 생성
    @PostMapping
    public ResponseEntity<ResponseDto<CommentResponseDto>> createComment(@Valid @RequestBody CommentCreateRequestDto dto) {
        ResponseDto<CommentResponseDto> response = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2)

}
