package com.jingwon.study.userservice.unit;

import com.jingwon.study.userservice.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private EmailService emailService;

    @Test
    @DisplayName("이메일 인증이 정상적으로 전송되어야 한다.")
    void should_send_verification_email(){
        String email = "jinkwon@example.com";
        String token = "verification-token";

        emailService.sendVerificationEmail(email,token);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertThat(sentMessage.getTo()).containsExactly(email);
        assertThat(sentMessage.getSubject()).isEqualTo("[Jingwon] 이메일 인증 요청");
        assertThat(sentMessage.getText()).contains("http://localhost:5051/verify-email?token=" + token);
    }
}
