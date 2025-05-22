package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.provider.JwtProvider;
import com.example.korea_sleepTech_springboot.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class MailSerivceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private MimeMessage createMail(String email, String token) throws MessagingException {
        // 메일 내용을 생성하는 메서드
        // : 메일 주소와 JWT 토큰을 매개변수로 받음
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail); // 발신자 이메일 주소 설정
        message.setRecipients(MimeMessage.RecipientType.TO, email); // 수신자 이메일 주소 설정

        message.setSubject("== 이메일 인증 링크 발송 ==");

        String body = """
                <h3>이메일 인증 링크입니다.<h3/>
                <a href="http://localhost:8080/api/v1/auth/verify?token=%s">여기를 클릭하여 인증을 완료해주세요.</a>
               
                """.formatted(token); // s 값에 formatted() 값이 대입됨

//        String body = "";
//        body += "<h3>이메일 인증 링크입니다.<h3/>";
//        body += "<a href=\"http://localhost:8080/api/v1/auth/verify?token=" + token + "\>여기를 클릭하여 인증을 완료해주세요.</a>"

        message.setText(body, "UTF-8", "html");
        return  message;
    }

    @Override
    // Mono: 리액테브 스트림의 단일 결과를 반환
    public  Mono<ResponseEntity<String>> sendSimpleMessage(String email) {
        // 블로킹 코드를 비동기 흐름 안에서 안전하게 실행하기 위해 사용
        // : 내부에서 예외 발생 가능성이 있는 동기 코드(JWT 생성, 이메일 생성/발송 등)를 넣고 Mono로 감싼다.
        return Mono.fromCallable(() -> {
        String token = jwtProvider.generateEmailValidToken(email);
        MimeMessage message = createMail(email, token);
        javaMailSender.send(message);
        return ResponseEntity.ok("인증 이메일이 전송되었습니다.");
            // 예외 처리
        }).onErrorResume(e -> Mono.just(
                ResponseEntity.badRequest().body("이메일 전송 실패: " + e.getMessage()))
                // 스케줄러 설정
                // : 이메일 전송, 파일 처리 등 I/O 작업에 적합한 스레드 풀
        ).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResponseEntity<String>> verifyEmail(String token) {
        return Mono.fromCallable(() -> {
            String email = jwtProvider.getUsernameFromJwt(token);
            return ResponseEntity.ok("이메일 인증이 완료되었습니다. 사용자: " + email);
        }).onErrorResume(e -> Mono.just(
                ResponseEntity.badRequest().body("이메일 인증 실패: " + e.getMessage())
        )).subscribeOn(Schedulers.boundedElastic());
    }


}
