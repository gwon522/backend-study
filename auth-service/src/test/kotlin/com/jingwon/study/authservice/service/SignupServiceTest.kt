package com.jingwon.study.authservice.service

import com.jingwon.study.authservice.repository.AuthUserRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SignupServiceTest {

    @Mock
    lateinit var authUserRepository: AuthUserRepository



}