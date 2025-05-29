package com.example.korea_sleepTech_springboot.common.constants;

// 응답 메시지를 정의하는 클래스
public class ResponseMessage {
    // 성공 메시지
    public static final String SUCCESS = "Success";

    // 존재 여부 관련 메시지
    public static final String NOT_EXIST_POST = "Post not found with id: ";
    public static final String NOT_EXIST_COMMENT = "Comment not found with id: ";

    public static final String NOT_EXIST_USER = "User not found";


    // 존재 관련 메시지
    public static final String EXIST_DATA = "Data already exists";

    // 인증 및 권한 관련 메시지
    public static final String NOT_MATCH_PASSWORD = "Password does not match";

}
