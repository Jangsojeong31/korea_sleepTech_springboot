package com.example.korea_sleepTech_springboot.dto._regex;

import com.example.korea_sleepTech_springboot.common.constants.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/*
== 정규 표현식 (Regular Expression) ==
: regex

1) 유효성 검증 흐름
    - 클라이언트에서 요청 시 데이터를 전달 (requestBody - DTO 명시)
    - Spring의 @Valid가 붙은 DTO에서 각 필드에 대한 정규 표현식 검사 수행
        >> 해당 표현식에 대해 위반 시 예외 발생 (MethodArgumentNotValidException 발생)
        >> 전역 예외 처리기(@ControllerAdvice)에서 예외 캐치 및 에러 메시지 가공 - 클라이언트에게 반환

2) 주요 개념
    - 리터럴 문자: 정확히 해당 문자열을 찾음
    ex) a, b, ab, abc 등
    - 메타 문자: . * + ? ^ % [] ()
    - 이스케이프 문자: \
        >> 메타 문자의 특수 의미를 제거하고 직접적인 문자로 사용
    - 수량자: * + ? {n, m}
        >> 반복 횟수를 지정
    - 경계: ^ $ \b
        >> 문자열의 시작, 끝, 단어 경계 지정

## 정규 표현식 검증은 'service 레이어'가 아니라
DTO 단에서 @Pattern, @NotBlank @Email 등의 어노테이션을 사용 ##

 */
public class RegexDto {
    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = RegexConstants.USER_ID, message = "아이디는 4~12자의 영문/숫자를 사용해야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = RegexConstants.PASSWORD,
            message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자 각각 하나 이상 포함"
    )
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    @Pattern(regexp = RegexConstants.KOREAN_NAME, message = "이름은 한글 2~10자 아내")
    private String userName;

//    phoneNumber: 휴대폰 번호 양식이 올바르지 않습니다.
    // nickname: 닉네임은 2~10자 영문/숫자/한글만 사용 가능합니다.

    @NotBlank
    @Email(message = "유효한 이메일 주소여야 합니다.") // 일반 정규표현식 사용 권장
    private String email;
}
