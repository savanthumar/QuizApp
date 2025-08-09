package com.quizapp.DAO;

import java.util.List;

import com.quizapp.model.Question;

public interface QuestionDAO {

	List<Question> getQuestionsBySubject(int subjectId);
    List<Question> getRandomQuestions(int subjectId, int limit);
}
