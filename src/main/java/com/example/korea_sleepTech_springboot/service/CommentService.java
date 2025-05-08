package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    ResponseDto<CommentResponseDto> createComment(@Valid CommentCreateRequestDto dto);
}
