package com.example.korea_sleepTech_springboot.common.constants;

// URL 설계 패턴 (Api Mapping Pattern)
// : RESTful하게 API 경로를 규칙적으로 설계하는 것
// - 각 Controller의 고유 경로를 지정
public class ApiMappingPattern {
    public static final String ORDER_API = "api/v1/orders";

    public static final String AUTH_API = "api/v1/auth"; // 단수처리
    public static final String USER_API = "api/v1/users";
    public static final String ADMIN_API = "api/v1/admin";

    public static final String BOOK_API = "/api/v1/user/books";

    public static final String POST_API = "/api/v1/posts";
//    public static final String COMMENT_API = "/api/v1/comments";

    // REST API 설계
    // 현재 구조) 댓글(Comment)이 Post(게시글) 엔티티에 포함 (1:N의 관계로 매핑)
    // >>> RESTful API의 설계 원칙: 종속적 데이터에 대해 하위 리소스 표현을 사용
    // 즉, 댓글의 CRUD는 게시글의 하위 리소스로 표현
    // 1) 댓글 생성(POST): /api/v1/posts/{postId}/comments
    // 2) 댓글 수정(PUT): /api/v1/posts/{postId}/comments/{commentId}
    // 3) 댓글 삭제(DELETE): /api/v1/posts/{postId}/comments/{commentId}
    public static final String COMMENT_API = "/api/v1/posts/{postId}/comments";
}
