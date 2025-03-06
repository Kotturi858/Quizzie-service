package com.lot.quiz.app.quizzie.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@JoinColumn(name = "question_text")
	private String questionText;
	// Other question-related fields
}
