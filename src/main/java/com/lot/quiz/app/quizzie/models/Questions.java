package com.lot.quiz.app.quizzie.models;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "QUESTIONS")
@Data
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questions_question_id_seq")
	@SequenceGenerator(name = "questions_question_id_seq", sequenceName = "questions_question_id_seq", allocationSize = 1)
	private Integer questionId;

	@Column(name = "question_text", nullable = false)
	private String questionText;

	@Column(name = "option_1", nullable = false, length = 255)
	private String option1;

	@Column(name = "option_2", nullable = false, length = 255)
	private String option2;

	@Column(name = "option_3", nullable = false, length = 255)
	private String option3;

	@Column(name = "option_4", nullable = false, length = 255)
	private String option4;

	@Column(name = "correct_answer", nullable = false)
	private Integer correctAnswer;

	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Language category;

	@Column(name = "difficulty")
	private Integer difficulty;
}
