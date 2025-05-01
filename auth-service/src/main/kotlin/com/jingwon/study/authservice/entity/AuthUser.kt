package com.jingwon.study.authservice.entity

import jakarta.persistence.*


@Entity
@Table(name = "auth_user")
data class AuthUser (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(nullable = false, unique = true)
        val email: String,
        @Column(nullable = false)
        val password: String,
        @Column(nullable = false)
        val emailVerified: Boolean = false,
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val provider: AuthProvider = AuthProvider.LOCAL,
        val providerId: String? = null, //oauth 필요
) : BaseEntity()