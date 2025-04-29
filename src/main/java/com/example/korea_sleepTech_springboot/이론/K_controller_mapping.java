package com.example.korea_sleepTech_springboot.이론;

public class K_controller_mapping {
    /*
    === Spring Controller 매핑에서 사용하는 주요 애너테이션 정리 ===
    1. @RequestParam
        : 클라이언트가 보낸 URL 쿼리 스트링 또는 폼 데이터를
            , 컨트롤러 메서드의 파라미터로 바인딩할때 사용
        - 주로 GET 요청에 많이 사용
        >> 간단한 검색 조건(URL 뒤에 간단한 데이터 전달), 필터링 & 페이징 기능, 보안이 크게 중요하지 않은 데이터
            : URL에 노출되기 때문에 민감한 정보는 비추천

        cf) 옵션 정리
        1) @RequestParam(required = true): 값이 없으면 오류 <기본>
        2) @RequestParam(required = false): 값이 없어도 허용
        3) @RequestParam(defaultValue = "값"): 기본값 설정

    2. @RequestBody
        : 클라이언트의 HTTP 요청 본문(Body)에 담긴 JSON, XML 데이터를
            자바 객체로 변환하여 메서드의 파라미터로 매핑할 때 사용
        - 주로 POST, PUT, DELETE 요청에 사용
        >> 복잡한 데이터 전송(객체 구조 필요), 민감한 데이터 전송에 이용
            : URL에 노출되지 않고 Body에 숨겨서 전송 가능

        cf) 주의할 점
        : 반드시! 요청 본문이 존재해야 함!
        - 클라이언트는 "Content_Type: application/json" 헤더 설정
        - DTO 객체는 반드시 getter/setter 또는 @Data 가 있어야 함

    3. @PathVariable
        : URL 경로 자체에 포함된 변수를 매핑하여 받는 애너테이션
        - 특정 리소스에 접근: "/books/{bookId}"
        - 특정 리소스 수정/삭제: "/books/{bookId}"

    4. @ AuthenticationPrincipal
        : 로그인한 사용자 정보를 컨트롤러 메서드 파라미터로 직접 가져오는 애너테이션
        - Spring Security가 인증된 사용자를 SecurityContext에 저장하고 해당 애너테이션이 인증 사용자 정보를 꺼내옴
        >> 로그인한 사용자 정보 필요, 로그인한 사용자의 ID로 조회


     */
}
