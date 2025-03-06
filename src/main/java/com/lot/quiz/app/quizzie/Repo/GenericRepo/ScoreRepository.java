package com.lot.quiz.app.quizzie.Repo.GenericRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.quiz.app.quizzie.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
	// Find the latest score for a specific user
	Score findTopByUserUserIdOrderByDateTakenDesc(Long userId);

	// Get historical scores for a user (for graph data)
	List<Score> findTop3ByUserUserIdOrderByDateTakenDesc(Long userId);

	// Get all scores for a specific user
	List<Score> findByUserUserIdOrderByScoreDesc(Long userId);

	// Get top scores across all users (for leaderboard)
	@Query("SELECT u.user_name, s.score, ROW_NUMBER() OVER (ORDER BY MAX(s.score) DESC) as rank "
			+ "FROM Score s JOIN s.user u GROUP BY u.user_name, s.score ORDER BY MAX(s.score) DESC LIMIT 4")
	List<Object[]> findTopScores();
}