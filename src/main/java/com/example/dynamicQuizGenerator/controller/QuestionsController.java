package com.example.dynamicQuizGenerator.controller;


import com.example.dynamicQuizGenerator.model.Question;
import com.example.dynamicQuizGenerator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    QuestionService qservice;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return qservice.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestinsByCategory(@PathVariable String category) {
        return qservice.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return qservice.addQuestion(question);
    }
}
