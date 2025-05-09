package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.reponse.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.entity.D_Comment;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.CommentRepository;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // import 주의

/*
@Transactional
: Spring Framework에서 제공하는 트랜잭션 관리 애너테이션
: DB의 일관성, 무결성, 원자성 등을 보장
- Spring의 AOP(관점 지향 프로그래밍)를 활용하여 메서드의 시작과 종료 시점에 트랜잭션을 시작하고,
종료 시점에 자동으로 커밋하거나 롤백 수행
>> 메서드가 정상적으로 실행되면 commit(), 예외가 발생하면 rollback()

cf) 조회(Read)의 경우: 내부 로직에서 변경 작업이 감지되면 예외가 발생하여 롤백 처리됨
*/

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
        // 댓글을 쓰려면 해당 Post가 있는지 먼저 확인해야함: PostRepository에서 가져와야함

    // 1) 댓글 생성
    @Override
    @Transactional(readOnly = false) // readOnly 속성: 읽지 전용 트랜잭션 설정 여부(기본값: false)
    public ResponseDto<CommentResponseDto> createComment(Long postId, CommentCreateRequestDto dto) {

        CommentResponseDto responseDto = null;

        // Post가 존재하는지 확인
        D_Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        // cf) D_Post의 addComment()를 직접 호출하지 않고, 직접적으로 post를 설정하여 양방항 관계를 수동으로 설정
        // 새로운 Comment 생성
        D_Comment newComment = D_Comment.builder()
                .content(dto.getContent())
                .commenter(dto.getCommenter())
//                .post(post) => addComment로 대체
                .build();

        post.addComment(newComment); // D_Comment가 D_Post에 추가되고 동시에 post 필드가 설정됨

        D_Comment savedComment = commentRepository.save(newComment);

//        responseDto = CommentResponseDto.builder()
//                .id(savedComment.getId())
//                .postId(savedComment.getPost().getId())
//                .content(savedComment.getContent())
//                .commenter(savedComment.getCommenter())
//                .build();

//        responseDto = new CommentResponseDto.Builder("내용(필수 값) ", "작성자(필수 값)")
//                .build();

        responseDto = new CommentResponseDto.Builder(savedComment.getContent(), savedComment.getCommenter())
                .id(savedComment.getId())
                .postId(savedComment.getPost().getId())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    // 2) 댓글 수정
    @Override
    @Transactional
    public ResponseDto<CommentResponseDto> updateComment(Long postId, Long commentId, CommentUpdateRequestDto dto) {

        CommentResponseDto responseDto = null;

        D_Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_COMMENT + commentId));

        if (!comment.getPost().getId().equals(postId)) { // 해당하는 comment의 post의 id가 일치하지 않는 경우
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        comment.setContent(dto.getContent());

        D_Comment updatedComment = commentRepository.save(comment);

//        responseDto = CommentResponseDto.builder()
//                .id(updatedComment.getId())
//                .postId(updatedComment.getPost().getId())
//                .content(updatedComment.getContent())
//                .commenter(updatedComment.getCommenter())
//                .build();

        responseDto = new CommentResponseDto.Builder(updatedComment.getContent(), updatedComment.getCommenter())
                .id(updatedComment.getId())
                .postId(updatedComment.getPost().getId())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    // 3) 댓글 삭제
    @Override
    @Transactional
    public ResponseDto<CommentResponseDto> deleteComment(Long postId, Long commentId) {
        D_Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_COMMENT + commentId));

        if (!comment.getPost().getId().equals(postId)) { // 해당하는 comment의 post의 id가 일치하지 않는 경우
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        // 연관 관계 해제
        comment.getPost().removeComment(comment);

        // DB에서 삭제
        commentRepository.delete(comment);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
