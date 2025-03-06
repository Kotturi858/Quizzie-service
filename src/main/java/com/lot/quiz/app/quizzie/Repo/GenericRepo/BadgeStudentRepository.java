package com.lot.quiz.app.quizzie.Repo.GenericRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lot.quiz.app.quizzie.models.BadgeStudent;

@Repository
public interface BadgeStudentRepository extends JpaRepository<BadgeStudent, Long> {
	List<BadgeStudent> findByUserUserId(Long userId);

	@Query("SELECT b.badgeName, CASE WHEN bs.id IS NOT NULL THEN true ELSE false END "
			+ "FROM Badge b LEFT JOIN BadgeStudent bs ON b.badgeId = bs.badge.badgeId AND bs.user.userId = :userId")
	List<Object[]> findUserBadgeAchievements(@Param("userId") Long userId);
}
