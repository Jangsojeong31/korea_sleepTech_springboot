package com.example.korea_sleepTech_springboot.이론;

public class HTTP02 {
    /*
    cf) 인터넷의 기본 개념
        1) IP(Internet Protocol) 주소: 각 장치의 고유 주소
        2) PORT: 하나의 IP에서 다양한 프로그램을 구분
        3) DNS: 도메인 이름 >> IP 주소로 변환
        4) TCP/IP: 신뢰성 있는 데이터 전송을 위한 통신 규약

        ex) localhost vs 127.0.0.1
        : localhost - 도메인 이름
        : 127.0.0.1 - IP 주소 (현재 기기의 로컬 IP 주소, 기기 자체에서만 사용 가능)

        cf) HTTP 기본 포트: 80
            HTTPS 기본 포트: 443
            +) Tomcat은 주로 8080 포트를 사용

        1. HTTP 메서드(Methods)
        : 요청이 어떤 목적을 가지고 있는지 알려주는 방법

        1) GET(가져오기)
            : 서버로부터 데이터를 가져올 것을 요청
        2) POST(생성하기)
            : 서버로부터 데이터를 저장할 것을 요청
        3) PUT(수정하기)
            : 서버로부터 데이터를 수정할 것을 요청
        4) DELETE(삭제하기)
            : 서버로부터 데이터를 삭제할 것을 요청

        ex) BoardController("/board")
        - GET + / (/board/)
            "모든 게시판 목록을 보여줘"
        - POST + / (/board/)
            "새로운 게시판을 등록할게"
        - PUT + /1 (/board/1)
            "1번 ID의 게시판을 수정할게"

    2. HTTP 상태 코드 (Status Code)
        : 요청에 대한 처리 결과를 숫자와 간단한 메시지로 알려줌
        1) 200 OK: 성공 (요청한 작업이 성공했다)
        2) 201 Created: 생성 성공 (새로운 데이터 생성에 성공했다)

        3) 400 Bad Request: 잘못된 요청 (ex. 주소값이 잘못된 경우)
        4) 401 Unauthorized: 인증 실패 (ex. 사원증 없이 회사 출입 금지)
        5) 403 Forbidden: 권한 없음 (ex. 회사 내 보안 문서는 권한 없이 열람 불가)
        6) 404 Not Found: 요청 자체를 찾을 수 없음 (ex. 해당하는 요청이 아예 없는 경우)
        cf) 개발자가 다룰 수 있는 에러: 400번 대

        7) 500 Internal Server Error: 서버 문제 (개발자가 해결할 수 x)

     */
}
