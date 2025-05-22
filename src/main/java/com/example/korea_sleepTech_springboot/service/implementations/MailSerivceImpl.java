package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.provider.JwtProvider;
import com.example.korea_sleepTech_springboot.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MailSerivceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private MimeMessage createMail(String email, String token) throws MessagingException {

    }

    @Override
    public  Mono<ResponseEntity<String>> sendSimpleMessage(String email) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<String>> verifyEmail(String token) {
        return null;
    }


}
