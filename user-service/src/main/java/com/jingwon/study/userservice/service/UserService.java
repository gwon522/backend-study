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

    @Transactional
    public void verifyEmailTokenAndActivateUser(String token) {
        User user = userRepository.findByEmailVerificationToken(token)
                .orElseThrow(() -> new IllegalStateException("유효하지 않은 토큰입니다."));
        user.activate();
        userRepository.save(user);
    }
}
