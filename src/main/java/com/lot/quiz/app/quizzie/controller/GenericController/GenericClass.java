/**
 *
 */
package com.lot.quiz.app.quizzie.controller.GenericController;

import com.lot.quiz.app.quizzie.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/quizzie/api/v1/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String savedUser = genericService.register(user.getUserName(), user.getEmail(), user.getPassword());
        return new ResponseEntity<String>(savedUser, HttpStatus.CREATED);
    }

}
