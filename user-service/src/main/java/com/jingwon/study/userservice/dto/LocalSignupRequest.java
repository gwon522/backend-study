package com.jingwon.study.userservice.dto;

import com.jingwon.study.userservice.domain.User;
import com.jingwon.study.userservice.domain.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public record LocalSignupRequest(String email,
                                String password,
                                String name,
                                UserRole role
) {
    public User toEntity(String verificationToken, PasswordEncoder encoder) {
        return User.createLocal(email, password, name, role, verificationToken, encoder);
    }
}
