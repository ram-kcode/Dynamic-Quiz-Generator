package com.example.dynamicQuizGenerator.controller;

import com.example.dynamicQuizGenerator.model.QuestionsWrapper;
import com.example.dynamicQuizGenerator.model.Response;
import com.example.dynamicQuizGenerator.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("/creat")
    public ResponseEntity<String> creatQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.creatQuiz(category, numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
