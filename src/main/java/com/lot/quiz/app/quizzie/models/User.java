package com.lot.quiz.app.quizzie.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    @NotBlank
    private String user_name;

    @Column(name = "email")
    @NotBlank
    @Email(message = "correct email format is required")
    private String email;

    @Column(name = "password")
    @NotBlank
    private transient String password;
}
