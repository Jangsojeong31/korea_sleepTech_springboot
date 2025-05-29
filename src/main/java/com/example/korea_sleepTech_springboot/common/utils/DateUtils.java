package com.example.korea_sleepTech_springboot.common.utils;

// JAVA의 LocalDateTime
//      : LocalDateTime을 문자열로 출력 -> YYYY-MM-DDTHH:mm:ss
// MySQL의 DateTime
//      : YYYY-MM-DD HH:mm:ss
// >> 두 언어 사이의 형식의 차이를 일치시키기 위함

/*
    날짜/시간 포맷 유틸 클래스
    : LocalDateTime - 긴 문자열 반환
    > 시스템 간 통신 및 로그 출격을 위한 통일된 포맷 제공
 */

import com.example.korea_sleepTech_springboot.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// === 포맷팅(formatting) === //
// : constants 패키지에서 정의 가능
public class DateUtils {
//    public static void main(String[] args) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    LocalDateTime now = LocalDateTime.now();
//    String formatted = now.format(formatter);
//
//        System.out.println(formatted);
//        // 2025-05-29 10:28:30
//    }

    // LocalDateTime >> 문자열
    public static String format(LocalDateTime dateTime) {
        return (dateTime != null) ? dateTime.format(FORMATTER) : null;
    }

    // 문자열 >> LocalDateTime
    public static LocalDateTime parse(String dateString) {
        return (dateString != null && !dateString.isEmpty()) ? LocalDateTime.parse(dateString, FORMATTER) : null;
    }

    // 현재 시간 문자열 반환
    public static String nowFormatted() {
        return format(LocalDateTime.now());
    }

}

class DateResponseDto {
    private String title;
    private String content;
    private String createdAt;

    public DateResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = DateUtils.format(post.getCreatedAt()); // 날짜 형식을 포맷터로 T 구분문자를 없애고 응답 반환
    }
}
