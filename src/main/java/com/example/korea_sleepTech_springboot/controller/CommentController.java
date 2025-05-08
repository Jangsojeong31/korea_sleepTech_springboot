package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.COMMENT_API) // "/api/v1/posts/{postId}/comments"
@RequiredArgsConstructor
public class CommentController {
    // 댓글
    // : CRUD 중 CUD만 처리 (조회는 Post 조회 시 댓글도 조회됨)

    private final CommentService commentService;

    // 1) 댓글 생성
    @PostMapping
    public ResponseEntity<ResponseDto<CommentResponseDto>> createComment(
            @Valid @RequestBody CommentCreateRequestDto dto,
            @PathVariable Long postId
    ) {
        ResponseDto<CommentResponseDto> response = commentService.createComment(postId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto<CommentResponseDto>> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto dto
    ){
        ResponseDto<CommentResponseDto> response = commentService.updateComment(postId, commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 3) 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto<Void>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        ResponseDto<CommentResponseDto> response = commentService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }

}
