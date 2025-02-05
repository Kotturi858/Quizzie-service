package com.lot.quiz.app.quizzie.service.GenericService;

import org.springframework.stereotype.Service;

import com.lot.quiz.app.quizzie.dto.OnloadDto;

@Service
public interface GenericService {
	public OnloadDto onload(String userId);
}
