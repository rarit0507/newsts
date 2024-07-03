package com.kh.practice.student.controller;

import com.kh.practice.student.model.vo.Student;

public class StudentController {

	public Student[] sArr = new Student[5];
	public static final int CUT_LINE = 60;
	
	// 객체배열 생성자
	public StudentController() {
		sArr[0] = new Student("김길동","자바",100);
		sArr[1] = new Student("박길동","디비",50);
		sArr[2] = new Student("이길동","화면",85);
		sArr[3] = new Student("정길동","서버",60);
		sArr[4] = new Student("홍길동","자바",20);
	}
	
	// 배열 데이터 반환
	public Student[] printStudent() {
		return sArr;
	}
	
	// 객체 배열 점수 합
	public int sumScore() {
		int i;			// index 초기화
		int tot = 0;	// tot(총합) 초기화
		
		for(i = 0; i < sArr.length; i++) {
			tot += sArr[i].getScore();
		}
		return tot;
	}
	
	// double[]로 sumScore의 리턴값 index 0에, 평균값 1번에
	public double[] avgScore() {
		double[] avgArr = new double[2];
		avgArr[0] = sumScore();
		avgArr[1] = (double)sumScore()/sArr.length;
		
		return avgArr;
	}
}