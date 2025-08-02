package com.lot.quiz.app.quizzie.service;

import org.springframework.stereotype.Service;

import com.lot.quiz.app.quizzie.dto.QuizResultDto;

@Service
public interface GenericService {
	public QuizResultDto onload(Long userId);
	public String register(String userName, String email, String password);

}
