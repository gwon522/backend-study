package com.jingwon.study.userservice.facade;

import com.jingwon.study.userservice.dto.LocalSignupRequest;
import com.jingwon.study.userservice.dto.OAuthSignupRequest;
import com.jingwon.study.userservice.service.EmailService;
import com.jingwon.study.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final EmailService emailService;

    public void signupLocalUser(LocalSignupRequest request) {
        //회원 가입여부 체크
        userService.checkDuplicateEmail(request.email());

        String verificationToken = UUID.randomUUID().toString();
        userService.createLocalUser(request,verificationToken);

        //메일 발송
        emailService.sendVerificationEmail(request.email(),verificationToken);
    }

    public void signupOAuthUser(OAuthSignupRequest request) {
    }
}
