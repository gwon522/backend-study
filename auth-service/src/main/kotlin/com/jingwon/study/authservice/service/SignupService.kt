package com.jingwon.study.authservice.service

import com.jingwon.study.authservice.dto.SignupRequest
import com.jingwon.study.authservice.entity.AuthProvider
import com.jingwon.study.authservice.entity.AuthUser
import com.jingwon.study.authservice.entity.EmailVerificationToken
import com.jingwon.study.authservice.repository.AuthUserRepository
import com.jingwon.study.authservice.repository.EmailVerificationTokenRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SignupService(
        private val authUserRepository: AuthUserRepository,
        private val emailVerificationTokenRepository: EmailVerificationTokenRepository,
        private val passwordEncoder: PasswordEncoder
) {
    fun signupLocal(request: SignupRequest){
        if(authUserRepository.existsByEmail(request.email)){
            throw IllegalArgumentException("이미 존재하는 이메일입니다.")
        }

        val authUser = AuthUser(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                provider = AuthProvider.LOCAL
        )

        authUserRepository.save(authUser)

        val token = UUID.randomUUID().toString()
        val tokenEntity =  EmailVerificationToken(
                token = token,
                authUser = authUser,
                expiredAt = LocalDateTime.now().plusMinutes(5)
        )
        emailVerificationTokenRepository.save(tokenEntity)

        //메일 전송로직 추가
    }
}