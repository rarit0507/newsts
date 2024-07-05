package com.kh.practice.student.model.vo;

public class Student {

	private String name;
	private String subject;
	private int score;
	
	//기본생성자
	public Student() {
	}
	
	// 초기값 생성자
	public Student(String name, String subject, int score) {
		super();
		this.name = name;
		this.subject = subject;
		this.score = score;
	}
	
	// inform 메서드 수정
    public String inform() {
        return "이름: " + name + ", 과목: " + subject + ", 점수: " + score;
    }
	
	// Getter Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	// toString
	@Override
	public String toString() {
		return "Student [name=" + name + ", subject=" + subject + ", score=" + score + "]";
	}
	
	
	
}
