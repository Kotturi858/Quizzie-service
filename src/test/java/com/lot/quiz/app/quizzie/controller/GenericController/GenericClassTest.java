package com.lot.quiz.app.quizzie.controller.GenericController;

import com.lot.quiz.app.quizzie.Repo.GenericRepo.UserRepository;
import com.lot.quiz.app.quizzie.models.User;
import com.lot.quiz.app.quizzie.service.GenericService;
import com.lot.quiz.app.quizzie.service.JwtService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = GenericClass.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        })
class GenericClassTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenericService genericService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Test for onload data retrieval")
    void registerUser_success() throws Exception {
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(genericService.register(anyString(), anyString(), anyString())).thenReturn("test@example.com");
        when(jwtService.generateToken(anyString(), anyLong())).thenReturn("token");

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzie/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").value("token"));
    }

    @Test
    @DisplayName("Test for user registration with existing email")
    void registerUser_userExists() throws Exception {
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(genericService.register(anyString(), anyString(), anyString())).thenReturn("User with this email already exists.");

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzie/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isAlreadyReported());
    }
}
