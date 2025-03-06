package com.lot.quiz.app.quizzie.Repo.GenericRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lot.quiz.app.quizzie.models.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
