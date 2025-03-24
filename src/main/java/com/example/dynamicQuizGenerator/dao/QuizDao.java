package com.example.dynamicQuizGenerator.dao;

import com.example.dynamicQuizGenerator.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


    
}