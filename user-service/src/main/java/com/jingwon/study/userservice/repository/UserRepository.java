package com.jingwon.study.userservice.repository;

import com.jingwon.study.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmailVerificationToken(String token);
}
