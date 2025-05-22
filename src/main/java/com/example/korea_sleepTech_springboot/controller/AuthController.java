package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.auth.PasswordResetRequestDto;
import com.example.korea_sleepTech_springboot.dto.auth.SendMailRequestDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpResponseDto;
import com.example.korea_sleepTech_springboot.service.AuthService;
import com.example.korea_sleepTech_springboot.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MailService mailService;

    private static final String POST_SIGN_UP = "/signup";
    private static final String POST_SIGN_IN = "/login";

    // 1) 회원가입
    // - HTTP 메서드: POST
    // - URI 경로: /signup
    // @Params: UserSignUpRequestDto
    // @Return: UserSignUpResponseDto
    @PostMapping(POST_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpResponseDto>> signup(@Valid @RequestBody UserSignUpRequestDto dto) {
        ResponseDto<UserSignUpResponseDto> response = authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 로그인
    // - HTTP 메서드: POST
    // cf) GET vs POST 중 POST 사용 권장: 로그인 과정에서 사용자 이름과 비밀번호와 같은 개인 정보를 서버로 전송하기 때문
    // GET 요청은 URL에 데이터가 노출(보통 조회는 데이터 구분값(PK) 사용)
    // - URI 경로: /signin
    // @Params: UserSignInRequestDto
    // @Return: UserSignInResponseDto
    @PostMapping(POST_SIGN_IN)
    public ResponseEntity<ResponseDto<UserSignInResponseDto>> login(@Valid @RequestBody UserSignInRequestDto dto) {
        ResponseDto<UserSignInResponseDto> response = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 3) 이메일 전송
    @PostMapping("/send-email")
    public Mono<ResponseEntity<String>> sendEmail(@Valid @RequestBody SendMailRequestDto dto) {
        return mailService.sendSimpleMessage(dto.getEmail());

    }

    // 4) 이메일 인증
    @GetMapping("/verify")
    public Mono<ResponseEntity<String>> verifyEmail(@RequestParam String token) {
        return mailService.verifyEmail(token);
    }

    // 5) 비밀번호 재설정
    @PostMapping("/reset-password")
    public Mono<ResponseEntity<String>> resetPassword(@Valid @RequestBody PasswordResetRequestDto dto) {
        return authService.resetPassword(dto);
    }
}
