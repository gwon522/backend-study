package com.jingwon.study.authservice.repository

import com.jingwon.study.authservice.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRepository : JpaRepository<AuthUser, Long> {
    fun existsByEmail(email: String) : Boolean
}