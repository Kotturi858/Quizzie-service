package com.lot.quiz.app.quizzie.dto;

import java.util.List;

import com.lot.quiz.app.quizzie.models.Language;

import lombok.Data;

@Data
public class OnloadDto {
	List<Language> lanuages;

	public List<Language> getLanuages() {
		return lanuages;
	}

	public void setLanuages(List<Language> lanuages) {
		this.lanuages = lanuages;
	}
	
	

}
