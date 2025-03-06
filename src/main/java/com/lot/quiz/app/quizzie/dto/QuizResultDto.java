package com.lot.quiz.app.quizzie.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuizResultDto {
	private int score;
	private List<LanguageDto> availableLanguages;
	private List<Integer> graphData;
	private List<BadgeDto> badgeData;
	private List<LeaderboardEntryDto> leaderBoard;

	// Getters
	public int getScore() {
		return score;
	}

	public List<LanguageDto> getAvailableLanguages() {
		return availableLanguages;
	}

	public List<Integer> getGraphData() {
		return graphData;
	}

	public List<BadgeDto> getBadgeData() {
		return badgeData;
	}

	public List<LeaderboardEntryDto> getLeaderBoard() {
		return leaderBoard;
	}

	// Setters
	public void setScore(int score) {
		this.score = score;
	}

	public void setAvailableLanguages(List<LanguageDto> availableLanguages) {
		this.availableLanguages = availableLanguages;
	}

	public void setGraphData(List<Integer> graphData) {
		this.graphData = graphData;
	}

	public void setBadgeData(List<BadgeDto> badgeData) {
		this.badgeData = badgeData;
	}

	public void setLeaderBoard(List<LeaderboardEntryDto> leaderBoard) {
		this.leaderBoard = leaderBoard;
	}
}
