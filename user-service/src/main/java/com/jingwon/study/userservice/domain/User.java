package com.jingwon.study.userservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name= "users" )
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Comment("유저 이메일")
    @Column(nullable = false, unique = true)
    private String email;
    @Comment("이메일 인증여부")
    @Column(nullable = false)
    private boolean emailVerified;
    @Comment("이메일 인증키")
    @Column(unique = true)
    private String emailVerificationToken;
    @Comment("비밀번호")
    @Column
    private String password;
    @Comment("이름")
    @Column(nullable = false)
    private String name;
    @Comment("유저구분")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Comment("회원가입방식")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider;
    @Comment("OAuth ID")
    @Column
    private String providerId;

    public void activate(){
        this.emailVerified = true;
    }
    public static User createLocal(String email, String rawPassword, String name, UserRole role, String token, PasswordEncoder encoder) {
        return User.builder()
                .email(email)
                .password(encoder.encode(rawPassword))
                .name(name)
                .role(role)
                .authProvider(AuthProvider.LOCAL)
                .emailVerified(false)
                .emailVerificationToken(token)
                .build();
    }
}
