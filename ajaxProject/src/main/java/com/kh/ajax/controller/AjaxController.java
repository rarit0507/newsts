package com.kh.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	/*
	 * 1. HttpServletResponse객체로 응답데이터를 응답하기(Stream을 이용한 방식)
	 * 아날로그한 방법임(Spring에서 잘 쓰지 않음)
	 */
	/*
	@GetMapping("ajax1.do")
	public void calSum(String menu,
					   int amount,
					   HttpServletResponse response) throws IOException {
		
		//System.out.println("사용자가 입력한 메뉴 : "+ menu);
		//System.out.println("사용자가 입력한 수량 : "+ amount);
		
		// 가정) 여기부터는 DB에서 일어나서 조회된 일
		int price = 0;
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "김치찜" : price = 12000; break;
		case "돈까스" : price = 15000; break;
		case "막국수" : price = 9000; break;
		case "서브웨이" : price = 8000;
		}
		
		price *= amount;
		
//		System.out.println(price);
		
		// 서비스 다녀와서 요청처리가 다 끝났다.
		// 요청한 페이지에 반환할 데이터를 완성해냈다!
		
		// (출력) 데이터 형식 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// 출력	// JAVA 출력을 위해서 OutputStream이나 Writer가 필요
		response.getWriter().print(price);
		
	}
	*/
	
	/*
	 * 2. 응답할 데이터를 문자열로 반환
	 * 	  => HttpServletResonse를 사용하지 않는 방법
	 * 		 => String 반환하면 포워딩, => 응답뷰의 경로로 인식을 해서 뷰 리졸버로 전달
	 * 
	 * 따라서 스프링을 사용해서 응답데이터를 반환할 때는
	 * 
	 * ==> MessageConverter로 이동하게끔 해주어야 함! ==>@ResponseBody 애노테이션~
	 * 종종 씀. 기억해둘 것.
	 * ID 중복체크 만들 수 있음!!! 
	 */
	@ResponseBody
	@GetMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
	public String calSum(String menu, int amount) {
		
		int price = 0;
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "김치찜" : price = 12000; break;
		case "돈까스" : price = 15000; break;
		case "막국수" : price = 9000; break;
		case "서브웨이" : price = 8000;
		}
		
		price *= amount;
		
		// 		/WEB-INF/views/			234234		.jsp
		
		return String.valueOf(price);	// + "한글";
	}

}
