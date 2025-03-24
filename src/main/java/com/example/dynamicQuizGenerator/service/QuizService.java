package com.example.dynamicQuizGenerator.service;

import com.example.dynamicQuizGenerator.dao.QuestionDao;
import com.example.dynamicQuizGenerator.dao.QuizDao;
import com.example.dynamicQuizGenerator.model.Question;
import com.example.dynamicQuizGenerator.model.QuestionsWrapper;
import com.example.dynamicQuizGenerator.model.Quiz;
import com.example.dynamicQuizGenerator.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> creatQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionsWrapper> questionsForUsers = new ArrayList <>();

        for(Question q: questionFromDb) {
            QuestionsWrapper qw = new QuestionsWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;

        for(Response response : responses) {
            if(response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
                right++;
            }

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
