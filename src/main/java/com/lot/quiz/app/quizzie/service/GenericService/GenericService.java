package com.lot.quiz.app.quizzie.service.GenericService;

import org.springframework.stereotype.Service;

import com.lot.quiz.app.quizzie.dto.OnloadDto;
import com.lot.quiz.app.quizzie.dto.QuizResultDto;

@Service
public interface GenericService {
	public QuizResultDto onload(Long userId);
	public String register(String userName, String email, String password);

}
