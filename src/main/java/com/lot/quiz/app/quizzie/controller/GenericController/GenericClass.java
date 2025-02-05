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

import com.lot.quiz.app.quizzie.dto.OnloadDto;
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
	public ResponseEntity<OnloadDto> getOnloadData(@RequestParam String userId) {
		OnloadDto data = genericService.onload(userId);
		return new ResponseEntity<OnloadDto>(data, HttpStatus.OK);
	}

}
