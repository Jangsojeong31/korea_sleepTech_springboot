package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.PostDetailResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostListResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostWithCommentCountResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.PostUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    ResponseDto<PostDetailResponseDto> createPost(@Valid PostCreateRequestDto dto);
    ResponseDto<PostDetailResponseDto> getPostById(Long id);
    ResponseDto<List<PostListResponseDto>> getAllPosts();
    ResponseDto<PostDetailResponseDto> updatePost(Long id, @Valid PostUpdateRequestDto dto);
    ResponseDto<Void> deletePost(Long id);

    ResponseDto<List<PostListResponseDto>> getPostByAuthor(String author);
    ResponseDto<List<PostListResponseDto>> searchPostsByTitle(String keyword);


    ResponseDto<List<PostWithCommentCountResponseDto>> getTop5PostByComments();
}
