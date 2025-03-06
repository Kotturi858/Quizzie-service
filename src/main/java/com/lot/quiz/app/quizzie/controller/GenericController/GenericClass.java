/**
 * 
 */
package com.lot.quiz.app.quizzie.controller.GenericController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lot.quiz.app.quizzie.dto.QuizResultDto;
import com.lot.quiz.app.quizzie.service.GenericServiceImpl.GenericServiceImpl;

/**
 * 
 */
@RestController
public class GenericClass {
	private GenericServiceImpl genericService;

	@Autowired
	public GenericClass(GenericServiceImpl genericService) {
		this.genericService = genericService;
	}

	@GetMapping("/quizzie/api/v1/onload-data")
	public ResponseEntity<QuizResultDto> getOnloadData(@RequestParam Long userId) {
		QuizResultDto data = genericService.onload(userId);
		return new ResponseEntity<QuizResultDto>(data, HttpStatus.OK);
	}

}
