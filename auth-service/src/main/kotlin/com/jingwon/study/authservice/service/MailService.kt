package com.jingwon.study.authservice.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class MailService (
        private val mailSender: JavaMailSender
){
    @Async // 트래픽이 많아지면 MQ 도입을 고려해볼 것
    fun sendVerificationMail(email: String, token: String){
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setTo(email)
        helper.setSubject("[회원가입] 이메일 인증을 완료해주세요")
        helper.setText(
                """
            <p>안녕하세요.</p>
            <p>이메일 인증을 완료하려면 아래 링크를 클릭해주세요:</p>
            <a href="https://your-domain.com/auth/verify-email?token=$token">이메일 인증하기</a>
            """.trimIndent(),
                true
        )

        mailSender.send(message)
    }
}