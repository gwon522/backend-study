package com.jingwon.study.authservice.controller

import com.jingwon.study.authservice.dto.SignupRequest
import com.jingwon.study.authservice.service.SignupService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (
        private val signupService: SignupService,
) {

    @PostMapping("/signup/local")
    fun signup(@RequestBody @Valid request: SignupRequest): ResponseEntity<Void> {
        signupService.signupLocal(request)
        return ResponseEntity.ok().build()
    }

}