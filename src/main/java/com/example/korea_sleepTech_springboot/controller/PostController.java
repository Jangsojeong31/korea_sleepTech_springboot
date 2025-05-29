package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.constants.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.reponse.PostDetailResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostListResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.PostWithCommentCountResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.PostUpdateRequestDto;
import com.example.korea_sleepTech_springboot.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST_API)
@RequiredArgsConstructor // 생성자 주입
public class PostController {

//    @Autowired // 의존성 자동 주입 - 필드 주입
    private final PostService postService;

    // 1) 게시글 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> createPost(@Valid @RequestBody PostCreateRequestDto dto){
        // @Valid: dto 객체에 대한 검증 수행, @NotBlank 등의 validation이 설정된 필드의 유효성 검증
        // : 미리 정의된 규칙에 맞지 않으면 에러 발생
        ResponseDto<PostDetailResponseDto> post = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // 2) 단건 조회 (댓글 포함 조회)
    // @params: 조회하고자 하는 댓글을 지정하는 고유 id - PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostDetailResponseDto> post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    // 3) 전체 조회 (댓글 제외)
    @GetMapping
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getAllPosts() {
        ResponseDto<List<PostListResponseDto>> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 4) 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequestDto dto
    ){
        ResponseDto<PostDetailResponseDto> response = postService.updatePost(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 5) 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deletePost(@PathVariable Long id){
        ResponseDto<Void> response = postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    // ==========================================
    // 메인경로: "/api/v1/posts"
    // 6) 특정 작성자의 모든 게시글 조회
    @GetMapping("/author/{author}")
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getPostByAuthor(@PathVariable String author) {
        ResponseDto<List<PostListResponseDto>> response = postService.getPostByAuthor(author);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 7) 특정 키워드로 제목 검색
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> searchPostsByTitle(@RequestParam String keyword) {
        ResponseDto<List<PostListResponseDto>> response = postService.searchPostsByTitle(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 8) 댓글이 가장 많은 상위 5개의 게시글 조회
    @GetMapping("/top-comments")
    public ResponseEntity<ResponseDto<List<PostWithCommentCountResponseDto>>> getTop5PostByComments() {
        ResponseDto<List<PostWithCommentCountResponseDto>> response = postService.getTop5PostByComments();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 9) 특정 키워드를 포함하는 댓글이 달린 게시글 조회
    //      ex. "스프링"이라는 키워드를 포함한 댓글이 달린 모든 게시글을 조회

    // 10) 특정 작성자의 게시글 중, 댓글 수가 일정 개수 이상인 게시글 조회
    //      ex. 특정 author가 작성한 게시글 중에서 댓글이 3개 이상인 게시글 조회
}
