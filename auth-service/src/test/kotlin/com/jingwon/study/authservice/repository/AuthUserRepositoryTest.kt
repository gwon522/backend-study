package com.jingwon.study.authservice.repository

import com.jingwon.study.authservice.entity.AuthProvider
import com.jingwon.study.authservice.entity.AuthUser
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class AuthUserRepositoryTest @Autowired constructor(
        val authUserRepository: AuthUserRepository
){
    @Test
    fun `이메일로 유저 존재 여부 확인`(){
        val email = "test@test.com"
        val user = AuthUser(
                email = email,
                password = "testPw",
                provider = AuthProvider.LOCAL
        )
        authUserRepository.save(user)

        val exists = authUserRepository.existsByEmail(email)
        Assertions.assertThat(exists).isTrue()
    }
}