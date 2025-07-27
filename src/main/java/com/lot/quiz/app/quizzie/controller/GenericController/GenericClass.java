/**
 *
 */
package com.lot.quiz.app.quizzie.controller.GenericController;

import com.lot.quiz.app.quizzie.Repo.GenericRepo.UserRepository;
import com.lot.quiz.app.quizzie.dto.QuizResultDto;
import com.lot.quiz.app.quizzie.models.User;
import com.lot.quiz.app.quizzie.service.GenericService.JwtService;
import com.lot.quiz.app.quizzie.service.GenericServiceImpl.GenericServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 */
@RestController
public class GenericClass {
    private final GenericServiceImpl genericService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GenericClass(GenericServiceImpl genericService, JwtService jwtService, UserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.genericService = genericService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/quizzie/api/v1/onload-data")
    public ResponseEntity<QuizResultDto> getOnloadData(@RequestParam Long userId) {
        QuizResultDto data = genericService.onload(userId);
        return new ResponseEntity<QuizResultDto>(data, HttpStatus.OK);
    }

    @PostMapping("/quizzie/api/v1/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody User user) {
        String savedUser = genericService.register(user.getUserName(), user.getEmail(), user.getPassword());
        if (!savedUser.equals("User with this email already exists.")) {
            AuthResponse authResponse = new AuthResponse("", "");
            authResponse.setAccessToken(jwtService.generateToken(savedUser, 360000));
            authResponse.setRefreshToken(jwtService.generateToken(savedUser, 36000000));
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<AuthResponse>((AuthResponse) null, HttpStatus.ALREADY_REPORTED);
    }

    @PostMapping("/quizzie/api/v1/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user, HttpServletResponse response) {
        // 1. Authenticate user
        Optional<User> validatedUser = userRepository.findByEmail(user.getEmail());
        if (!passwordEncoder.matches(user.getPassword(), validatedUser.get().getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());


        // Generate JWT token
        String accessToken = jwtService.generateToken(userDetails.getUsername(), 36000);
        String refreshToken = jwtService.generateToken(userDetails.getUsername(), 3600000);

        // 2. Set cookies
        Cookie accessCookie = new Cookie("access_token", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true); // use false in localhost if needed
        accessCookie.setPath("/");
        accessCookie.setMaxAge(15 * 60);

        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/"); // optionally set to /refresh
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        // Return token
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

}
