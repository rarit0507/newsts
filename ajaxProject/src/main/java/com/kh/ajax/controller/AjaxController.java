package com.kh.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Menu;

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
	
	
	@ResponseBody	//
	@GetMapping(value="ajax2.do", produces="application/json; charset=UTF-8")	//application/json; : 내가 보낼 데이터 타입을 JSON으로 보내겠다!!!
	public String selectMenu(int menuNumber) {
		
		// 요청처리를 잘했다는 가정 하에~~~ 데이터 응답
		
		/*
		 * DB에 존재하는 메뉴 테이블
		 * --------------------------------------------
		 * | 메뉴번호 | 메뉴이름 |  가격  |  재료  |
		 * |     1    |  순두부  |  9500  | 순두부 |
		 * ....
		 * --------------------------------------------
		 * => 왜 조회해? : 화면에 출력하려고(4개)
		 * => VO객체는 >>>자바의 객체(메모리주소값)<<<임... 우리는 지금 >>>자바스크립트<<<한테 값을 전달해야 한다.
		 * 
		 * 	  * 옛날에는 xml로 넘겼다.
		 * 		<menuNumber>1</menuNumber>
		 * 		<menuName>순두부</menuName>
		 * 		<price>9500</price>
		 * 		<material>순두부</material>
		 * 
		 * 지금은 >>>!!! ★-- JSON(Java Script Object Notaion) --★ !!!<<< : 자바스크립트 객체 표기법
		 * 
		 * [] : 배열형태
		 * {} : 객체형태
		 * 
		 * But. JAVA와 JSON은 연관이 없다 !!
		 */
		
		/* 
		 * 예시
		 * {
		 * "menuNumber" : "1",
		 * "menuName" : "순두부",
		 * "price" : "9500",
		 * "material" : "순두부"
		 * }
		 */
		
		// 요청 보내서 결과 잘 받았음.
		// menuService.findByMenuNumber(menuNumber);
		Menu menu = new Menu(1, "순두부", 9500, "순두부");	// 자바스크립트가 해석할 수 있는 형태로 보내줘야 함!
		
		/*
		// 자바스크립트 형태로 보내라고 하면 보낼 수는 있음...
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("menuNumber :" + "'" + menu.getMenuNumber() + "',");
		sb.append("menuName :"   + "'" + menu.getMenuName()   + "',");
		sb.append("price :"      + "'" + menu.getPrice()      + "',");
		sb.append("material :"   + "'" + menu.getMaterial()   + "',");
		sb.append("}");
		
		return sb.toString();
		*/
		
		JSONObject jObj = new JSONObject();		//JSON 객체를 만들어서
		jObj.put("menuNumber", menu.getMenuNumber());	// .put(Key, Value);	// Controller에서 작업함 = JAVA에서 작업한 것
		jObj.put("menuName", menu.getMenuNumber());
		jObj.put("price", menu.getPrice());
		jObj.put("material", menu.getMaterial());
		
		// HashMap map = new HashMap();
		// map.toString();
		// Map은 .put() 메서드 써서 요소 추가함.
		// = JSONObject는 >>>맵Map<<<이다!!!
		
		//{menuNumber: '1', menuName: '순두부', price: '9500', material: '순두부'}
		
		return jObj.toJSONString();
	}
	
	
	//필드가 많으면 어쩔건데 -> Gson
	@ResponseBody	//
	@GetMapping(value="ajax3.do", produces="application/json; charset=UTF-8")	//application/json; : 내가 보낼 데이터 타입을 JSON으로 보내겠다!!!
	public String ajaxMethod(int menuNumber) {
		
		Menu menu = new Menu(menuNumber, "순두부찌개", 9500, "순두부");
		// DB에서 SELECT해옴
		
		return new Gson().toJson(menu);	//SimpleJSON 라이브러리가 ajax2.do에 쓰인 것. 이걸 편하게 해주는 게 Gson
	}

	@ResponseBody
	@GetMapping(value="find.do", produces="application/json; charset=UTF-8")
	public String findAll() {
		
		List<Menu> menus = new ArrayList();
		menus.add(new Menu(1, "순두부찌개", 9500, "순두부"));
		menus.add(new Menu(2, "김치찌개", 12000, "김치"));
		menus.add(new Menu(3, "된장찌개", 8000, "된장"));
		//selectList 조회결과가 menus에 담겨있음.
		//List<Menu> list = menuService.findAll();
		
		/*
		 * 1. 객체 3개가 필요.
		 * ]
		 * 	{
		 * 		menuNumber : 1,
		 * 		menuName : "순두부찌개",
		 * 		price : 9500,
		 * 		material : "순두부"
		 * 	}
		 * 	{
		 * 		menuNumber : 2,
		 * 		menuName : "김치찌개",
		 *		price : 12000,
		 * 		material : "김치"
		 * 	}
		 * 	{
		 * 		menuNumber : 3,
		 * 		menuName : "된장찌개",
		 * 		price : 8000,
		 * 		material : "된장"
		 * 	}
		 * ]
		 * 2. 이 3개 객체를 따로 보낼 수는 없다. 묶어서 보내야
		 *    => JavaScript에서 >>>배열<<<에 담음
		 */
		/*
		JSONObject jObj1 = new JSONObject();		//JSON 객체를 만들어서
		jObj1.put("menuNumber", menus.get(0).getMenuNumber());	// .put(Key, Value);	// Controller에서 작업함 = JAVA에서 작업한 것
		jObj1.put("menuName", menus.get(0).getMenuNumber());
		jObj1.put("price", menus.get(0).getPrice());
		jObj1.put("material", menus.get(0).getMaterial());
		
		JSONObject jObj2 = new JSONObject();	
		jObj2.put("menuNumber", menus.get(1).getMenuNumber());
		jObj2.put("menuName", menus.get(1).getMenuNumber());
		jObj2.put("price", menus.get(1).getPrice());
		jObj2.put("material", menus.get(1).getMaterial());
		
		JSONObject jObj3 = new JSONObject();
		jObj3.put("menuNumber", menus.get(2).getMenuNumber());
		jObj3.put("menuName", menus.get(2).getMenuNumber());
		jObj3.put("price", menus.get(2).getPrice());
		jObj3.put("material", menus.get(2).getMaterial());
		*/
		// 이렇게 넣지 말고 반복문을 써라
		
		// JSONArray로 묶어서 보내야 함. (ArrayList랑 같음.)
		// JSONArray jArr = new JSONArray();
		/*
		jArr.add(jObj1);
		jArr.add(jObj2);
		jArr.add(jObj3);
		*/
		
		/* 반복문
		for(int i = 0; i < menus.size(); i++) {
			JSONObject jObj = new JSONObject();		//JSON 객체를 만들어서
			jObj.put("menuNumber", menus.get(0).getMenuNumber());	// .put(Key, Value);	// Controller에서 작업함 = JAVA에서 작업한 것
			jObj.put("menuName", menus.get(0).getMenuNumber());
			jObj.put("price", menus.get(0).getPrice());
			jObj.put("material", menus.get(0).getMaterial());
			
			jArr.add(jObj);
		}
		*/
		
		//이것도 귀찮아 =>  Gson
		return new Gson().toJson(menus);
	}
	// Key값은 VO의 필드명과 동일하게!!
	
}
