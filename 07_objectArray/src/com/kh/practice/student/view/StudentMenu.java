package com.kh.practice.student.view;

import com.kh.practice.student.controller.StudentController;
import com.kh.practice.student.model.vo.Student;

public class StudentMenu {

	public StudentMenu() {
		StudentController st = new StudentController();

		System.out.println("========== 학생 정보 출력 ==========");
		Student[] sArr = st.printStudent();
		for(int i=0; i < sArr.length ;i++) {
			System.out.println(sArr[i].inform());
		}
		
		System.out.println("========== 학생 성적 출력 ==========");
		double[] scoreArr = st.avgScore();
		System.out.println("학생 점수 합계 : " + scoreArr[0]);
		System.out.println("학생 점수 평균 : " + scoreArr[1]);
		
		System.out.println("========== 성적 결과 출력 ==========");
		for(int i=0; i < sArr.length; i++) {
			if(sArr[i].getScore() < st.CUT_LINE) {
				System.out.println(sArr[i].getName() + " 학생은 재시험 대상입니다");
			} else {
				System.out.println(sArr[i].getName() + " 학생은 통과입니다");
			}
		}
	}
}
