package com.jingwon.study.authservice.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
        @CreatedDate
        @Column(nullable = false, updatable = false)
        var createdAt: LocalDateTime = LocalDateTime.now()
            protected set
        @LastModifiedDate
        @Column(nullable = false)
        var updatedAt: LocalDateTime = LocalDateTime.now()
            protected set
        @Column(nullable = false)
        var delYn: Boolean = false
            protected set
}