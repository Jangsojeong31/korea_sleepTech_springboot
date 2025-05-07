package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// @Data 어노테이션
// : @Getter, @Setter, @ToString, @RequiredConstructor 등을 포함
@Entity
@Table(name = "post")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// ToString의 exclude 속성: 해당 속성값의 필드를 제외하고 ToString 메서드 내에서 필드값 출력
@ToString(exclude = "comments")
public class D_Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String author;
    // 게시글 1개당 여러 개의 댓글이 작성(1:N)
    /*
    Post 와 Comment 는 1:N의 관계
    - Post가 하나일 때 여러 개의 Comment가 연결
    속성들)
    1) mappedBy = "필드명"
        : D_Comment 엔티티의 'post' 필드와 매핑 (참조하는 테이블의 필드명)

    2) cascade = CascadeType.ALL
        : Post가 삭제되면 연결된 모든 Comment를 자동으로 함께 삭제 (게시글 자체의 삭제)

    3) orphanRemoval = true
        : List에서 제거된 Comment 객체는 자동으로 삭제됨 (게시글 내의 댓글 삭제)

    4) fetch = FetchType.LAZY
        : Post를 조회할 때 Comment 목록이 즉시 조회되지 않고, 필요할 때 로딩
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<D_Comment> comments = new ArrayList<>();

    /*
    연관 관계 편의 메서드 - 1
        : Comment를 Post에 추가할 때 사용
        - comments 리스트에 Comment를 추가, 해당 Comment의 post 필드에 현재 Post 객체를 설정

    @param: comment 추가할 D_Comment 객체

     */
    public void addComment(D_Comment comment){
        this.comments.add(comment);
        comment.setPost(this);
    }

    /*
    연관 관계 편의 메서드 - 2
        : Comment를 Post에 제거할 때 사용
        - comments 리스트에서 Comment를 제거, 해당 Comment의 post 필드를 null로 설정

     */
    public void removeComment(D_Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null); // Comment 객체의 연관 관계를 해제
    }


}
