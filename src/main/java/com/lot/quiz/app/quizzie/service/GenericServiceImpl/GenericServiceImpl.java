package com.lot.quiz.app.quizzie.service.GenericServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.quiz.app.quizzie.Repo.GenericRepoImpl.GenericRepoImpl;
import com.lot.quiz.app.quizzie.dto.OnloadDto;

@Service
public class GenericServiceImpl {
	private GenericRepoImpl genericRepoImpl;

	@Autowired
	public GenericServiceImpl(GenericRepoImpl genericRepoImpl) {
		this.genericRepoImpl = genericRepoImpl;
	}

	public OnloadDto onload(String userId) {
		OnloadDto onloadDto = new OnloadDto();
		onloadDto.setLanuages(genericRepoImpl.findAll());

		return onloadDto;
	}
}
