package com.example.korea_sleepTech_springboot.common;

// URL 설계 패턴 (Api Mapping Pattern)
// : RESTful하게 API 경로를 규칙적으로 설계하는 것
// - 각 Controller의 고유 경로를 지정
public class ApiMappingPattern {
    public static final String BOOK_API = "/api/v1/books";

    public static final String POST_API = "/api/v1/posts";
    public static final String COMMENT_API = "/api/v1/comments";
}
