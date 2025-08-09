package com.quizapp.DAO;

import java.util.List;

import com.quizapp.model.Subject;

public interface SubjectDAO {

	
	List<Subject> getAllSubjects();
    Subject getSubjectById(int id);
}
