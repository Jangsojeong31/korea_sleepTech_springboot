package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    ResponseDto<CommentResponseDto> createComment(Long postId, @Valid CommentCreateRequestDto dto);

    ResponseDto<CommentResponseDto> updateComment(Long postId, Long commentId, CommentUpdateRequestDto dto);

    ResponseDto<CommentResponseDto> deleteComment(Long postId, Long commentId);
}
