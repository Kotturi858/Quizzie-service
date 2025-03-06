package com.lot.quiz.app.quizzie.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "badges_students")
public class BadgeStudent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "badge_id")
	private Badge badge;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User user;
	
}
