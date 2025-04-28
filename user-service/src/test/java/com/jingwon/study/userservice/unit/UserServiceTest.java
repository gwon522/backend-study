package com.jingwon.study.userservice.unit;

import com.jingwon.study.userservice.repository.UserRepository;
import com.jingwon.study.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

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
}
