package com.lot.quiz.app.quizzie.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_id")
	private Long userId;

	private String name;
	// Other user-related fields
}
