package com.jingwon.study.authservice.repository

import com.jingwon.study.authservice.entity.EmailVerificationToken
import org.springframework.data.jpa.repository.JpaRepository

interface EmailVerificationTokenRepository : JpaRepository<EmailVerificationToken, Long> {
}