package com.quizapp.model;

public class Subject {
	
	private int id;
    private String subjectName;
    
    public Subject() {
		// TODO Auto-generated constructor stub
	}

	public Subject(int id, String subjectName) {
		
		this.id = id;
		this.subjectName = subjectName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
    
    

}
