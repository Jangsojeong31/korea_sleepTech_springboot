package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostDetailResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostListResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    // 1) 게시글 생성
    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> createPost(PostCreateRequestDto dto) {

        PostDetailResponseDto responseDto = null;

        D_Post newPost = D_Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build(); // comments 필드는 null 값이 가능, 필요시 제외

        D_Post savedPost = postRepository.save(newPost);

        responseDto = PostDetailResponseDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .author(savedPost.getAuthor())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    // 2) 단건 조회
    @Override
    @Transactional(readOnly = true) // DB값이 변경되려면 롤백됨
    public ResponseDto<PostDetailResponseDto> getPostById(Long id) {
        PostDetailResponseDto responseDto = null;

        D_Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        List<CommentResponseDto> comments = post.getComments().stream() // post.getComments()는 List<D_Comment>를 반환하기 때문에 변환이 필요
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .postId(comment.getPost().getId())
                        .content(comment.getContent())
                        .commenter(comment.getCommenter())
                        .build())
                .collect(Collectors.toList());

        responseDto = PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .comments(comments)
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);

    }

    // 3) 전체 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        List<PostListResponseDto> responseDtos = null;

        List<D_Post> posts = postRepository.findAll();

        responseDtos = posts.stream()
                .map(post -> PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }
}
