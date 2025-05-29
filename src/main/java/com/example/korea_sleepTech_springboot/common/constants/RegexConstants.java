package com.example.korea_sleepTech_springboot.common.constants;

public interface RegexConstants {
    // 이메일 정규식 (간단한 버전)
    public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    // 비밀번호: 최소 8자 이상, 영문, 숫자, 특수문자 각각 하나 이상 포함
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    // 휴대폰 번호: 010으로 시작하고 숫자 총 11자리
    public static final String PHONE = "^010\\d{8}$";

    // 사용자 ID: 영문자로 시작하고 영문자 또는 숫자 4~12자
    public static final String USER_ID = "^[a-zA-Z][a-zA-Z0-9]{3,11}$";

    // 사용자 이름: 한글 2~10자
    public static final String KOREAN_NAME = "^[가-힣]{2,10}$";

    // ..... //
}
