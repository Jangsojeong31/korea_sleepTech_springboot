package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "post")
public class D_Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Comment 와 Post는 '다대일' 의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    // N:1의 관계
    // fetch = FetchType.LAZY - Comment를 조회할 때 Post 즉시 로딩되지 않고, 필요할 때 로딩
    @JoinColumn(name = "post_id")
    // DB에서 외래키 컬럼명을 명시
    private D_Post post;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String commenter;
}
