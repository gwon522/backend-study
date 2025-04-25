package com.jingwon.study.userservice.service;

import com.jingwon.study.userservice.domain.User;
import com.jingwon.study.userservice.dto.LocalSignupRequest;
import com.jingwon.study.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    @Transactional
    public void createLocalUser(LocalSignupRequest request, String verificationToken) {
        User user = request.toEntity(verificationToken, passwordEncoder);
        userRepository.save(user);
    }
}
