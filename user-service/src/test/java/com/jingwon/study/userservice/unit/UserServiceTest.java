package com.jingwon.study.userservice.unit;

import com.jingwon.study.userservice.domain.User;
import com.jingwon.study.userservice.domain.UserRole;
import com.jingwon.study.userservice.dto.LocalSignupRequest;
import com.jingwon.study.userservice.repository.UserRepository;
import com.jingwon.study.userservice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("중복된 이메일이면 예외가 발생해야 한다")
    void should_throw_exception_when_email_is_duplicated() {
        String email = "jinkwon@test.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        // when & then
        assertThrows(IllegalStateException.class, () -> userService.checkDuplicateEmail(email));
    }


    @Test
    @DisplayName("중복되지 않은 이메일이면 정상 통과해야 한다")
    void should_pass_when_email_is_not_duplicated() {
        // given
        String newEmail = "unique@test.com";
        // when & then
        assertDoesNotThrow(() -> userService.checkDuplicateEmail(newEmail));
    }

    @Test
    @DisplayName("로컬 유저 생성 시 비밀번호 인코딩 및 저장이 정상 동작해야 한다")
    void should_create_local_user_successfully() {
        // given
        LocalSignupRequest request = new LocalSignupRequest("jinkwon@test.com", "testPassword", "이진권", UserRole.CUSTOMER);
        String verificationToken = "sample-token";

        // mock passwordEncoder.encode()
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");

        // when
        userService.createLocalUser(request, verificationToken);

        // then
        verify(userRepository).save(any(User.class));
    }
}
