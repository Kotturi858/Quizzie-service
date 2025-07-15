package com.lot.quiz.app.quizzie.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    @NotBlank
    private String userName;

    @Column(name = "email")
    @NotBlank
    @Email(message = "correct email format is required")
    private String email;

    @Column(name = "password_hash")
    @NotBlank
    private String password;
}
