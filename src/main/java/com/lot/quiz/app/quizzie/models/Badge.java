package com.lot.quiz.app.quizzie.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "badges")
public class Badge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long badgeId;

	private String badgeName;
	private String description;
	// Other badge-related fields
}
