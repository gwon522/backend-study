package com.jingwon.study.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Async
    public void sendVerificationEmail(String email, String verificationToken) {
        String subject = "[Jingwon] 이메일 인증 요청";
        String verificationLink = "http://localhost/verify-email?token=" + verificationToken;

        String body = String.format(
            """
            안녕하세요, Jingwon 서비스입니다.

            아래 링크를 클릭하여 이메일 인증을 완료해주세요:

            %s

            감사합니다.
            """,
            verificationLink
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
