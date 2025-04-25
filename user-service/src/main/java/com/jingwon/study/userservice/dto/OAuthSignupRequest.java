package com.jingwon.study.userservice.dto;

import com.jingwon.study.userservice.domain.AuthProvider;
import com.jingwon.study.userservice.domain.UserRole;

public record OAuthSignupRequest(String email,
                                 String providerId,
                                 AuthProvider authProvider,
                                 String name,
                                 UserRole role
) {}
