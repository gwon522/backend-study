package com.jingwon.study.authservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "email_verification_token")
data class EmailVerificationToken (
        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false, unique = true)
        val token: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="auth_user_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        val authUser: AuthUser,

        @Column(nullable = false)
        val expiredAt: LocalDateTime,

        @Column(nullable = false)
        var used: Boolean =false

) : BaseEntity()