package com.kh.spring.string.run;

import com.kh.spring.string.controller.StringController;

public class StringRun {

	public static void main(String[] args) {

		StringController sc = new StringController();
		
		sc.constructorString();
		
		//System.out.println(sc.toString());
		sc.equals(null);
		
		//sc.assignToString();
		
		sc.stringPool();
	}

}
