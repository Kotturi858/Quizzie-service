package com.lot.quiz.app.quizzie.service.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.quiz.app.quizzie.Repo.GenericRepo.BadgeRepository;
import com.lot.quiz.app.quizzie.Repo.GenericRepo.BadgeStudentRepository;
import com.lot.quiz.app.quizzie.Repo.GenericRepo.LanguageRepository;
import com.lot.quiz.app.quizzie.Repo.GenericRepo.ScoreRepository;
import com.lot.quiz.app.quizzie.dto.BadgeDto;
import com.lot.quiz.app.quizzie.dto.LanguageDto;
import com.lot.quiz.app.quizzie.dto.LeaderboardEntryDto;
import com.lot.quiz.app.quizzie.dto.QuizResultDto;
import com.lot.quiz.app.quizzie.models.Language;
import com.lot.quiz.app.quizzie.models.Score;

@Service
public class GenericServiceImpl {

	@Autowired
	private BadgeRepository badgeRepository;

	@Autowired
	private BadgeStudentRepository badgeStudentRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	public QuizResultDto onload(Long userId) {
		QuizResultDto resultDto = new QuizResultDto();

		// 1. Get latest score
		Score latestScore = scoreRepository.findTopByUserUserIdOrderByDateTakenDesc(userId);
		resultDto.setScore(latestScore != null ? latestScore.getScore() : 0);

		// 2. Get available languages
		List<Language> languages = languageRepository.findAll();
		resultDto.setAvailableLanguages(
				languages.stream().map(lang -> new LanguageDto(lang.getLanguageId(), lang.getLanguageName()))
						.collect(Collectors.toList()));

		// 3. Get graph data (last 3 scores)
		List<Score> recentScores = scoreRepository.findTop3ByUserUserIdOrderByDateTakenDesc(userId);
		List<Integer> mappedies = new ArrayList<Integer>();
		mappedies.add(recentScores.get(0).getCorrectQuestions());
		mappedies.add(recentScores.get(0).getIncorrectQuestions());
		mappedies.add(recentScores.get(0).getSkippedQuestions());
		
		resultDto.setGraphData(mappedies);

		// 4. Get badge data
		List<Object[]> badgeAchievements = badgeStudentRepository.findUserBadgeAchievements(userId);
		List<BadgeDto> badgeDtos = new ArrayList<>();

		for (Object[] row : badgeAchievements) {
			String badgeName = (String) row[0];
			boolean isAchieved = (boolean) row[1];
			badgeDtos.add(new BadgeDto(badgeName, isAchieved));
		}
		resultDto.setBadgeData(badgeDtos);

		// 5. Get leaderboard
		List<Object[]> leaderboardData = scoreRepository.findTopScores();
		List<LeaderboardEntryDto> leaderboardEntries = new ArrayList<>();

		for (Object[] row : leaderboardData) {
			String name = (String) row[0];
			int score = ((Number) row[1]).intValue();
			int rank = ((Number) row[2]).intValue();
			leaderboardEntries.add(new LeaderboardEntryDto(rank, name, score));
		}
		resultDto.setLeaderBoard(leaderboardEntries);

		return resultDto;
	}

	public void register(String userName, String email, String password) {

	}
}
