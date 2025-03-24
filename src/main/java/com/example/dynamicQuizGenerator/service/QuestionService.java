package com.example.dynamicQuizGenerator.service;

import com.example.dynamicQuizGenerator.dao.QuestionDao;
import com.example.dynamicQuizGenerator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questiondao;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try {
            return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questiondao.save(question);
        return new ResponseEntity<>("question saved successfully", HttpStatus.CREATED);

    }
}
