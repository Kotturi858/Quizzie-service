package com.lot.quiz.app.quizzie.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigOriginal{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Using lambda for CSRF configuration
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/quizzie/api/v1/register", "/public/**").permitAll() // Modern way to configure
                        .anyRequest().authenticated()
                )
                // You can configure other things like formLogin, httpBasic, etc. here
                .formLogin(Customizer.withDefaults()) // Use Customizer.withDefaults() for default login behavior
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
