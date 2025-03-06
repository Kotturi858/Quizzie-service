package com.lot.quiz.app.quizzie.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "scores")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "score_id")
	private Long scoreId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	private Integer score;

	@Column(name = "date_taken")
	private LocalDateTime dateTaken;

	@Column(name = "correct_ques")
	private Integer correctQuestions;

	@Column(name = "incorrect_ques")
	private Integer incorrectQuestions;

	@Column(name = "skipped_ques")
	private Integer skippedQuestions;

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public LocalDateTime getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(LocalDateTime dateTaken) {
		this.dateTaken = dateTaken;
	}

	public Integer getCorrectQuestions() {
		return correctQuestions;
	}

	public void setCorrectQuestions(Integer correctQuestions) {
		this.correctQuestions = correctQuestions;
	}

	public Integer getIncorrectQuestions() {
		return incorrectQuestions;
	}

	public void setIncorrectQuestions(Integer incorrectQuestions) {
		this.incorrectQuestions = incorrectQuestions;
	}

	public Integer getSkippedQuestions() {
		return skippedQuestions;
	}

	public void setSkippedQuestions(Integer skippedQuestions) {
		this.skippedQuestions = skippedQuestions;
	}
}
