package com.jingwon.study.authservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
        @field:Email
        val email: String,

        @field:NotBlank
        val password: String,

        @field:NotBlank
        val name: String
)
