package com.lot.quiz.app.quizzie.service;

import com.lot.quiz.app.quizzie.models.User;
import com.lot.quiz.app.quizzie.Repo.GenericRepo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenericServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private GenericServiceImpl genericServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genericServiceImpl = new GenericServiceImpl(passwordEncoder);
        genericServiceImpl.userRepository = userRepository;
    }

    @Test
    void testRegister_UserAlreadyExists() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));
        String result = genericServiceImpl.register("user", email, "password");
        assertEquals("User with this email already exists.", result);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegister_NewUser() {
        String userName = "user";
        String email = "test2@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setUserName(userName);
            return user;
        });
        String result = genericServiceImpl.register(userName, email, password);
        assertEquals(userName, result);
        verify(userRepository).save(any(User.class));
    }
}

