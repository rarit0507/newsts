package com.kh.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.kh.api.model.vo.AirVO;

public class ApiJavaApp {
	
	public static final String SERVICE_KEY = "SaWv7aufqqQ4eySc2FZM%2BL7oYEhYGai2gRMMmHc%2FsI%2BD4mYGpTIaDtX%2FPBDhqt1XRRPdaYveF1Awht%2BEcpKiUA%3D%3D";
	
	public static void main(String[] args) throws IOException {
		
		
		// System.out.println("ㅎㅇ");
		// 순수 Java만으로 Client Program을 만들어서 시도별 API 서버로 요청 보내고 응답 받기!

		// 요청을 보낼 URL이 필요함!!! => String타입으로 만들 것
			// 공식 문서에서 'Call Back URL' 복사 후 
			// RequestParameter 붙여야 함 (필수항목, 리턴타입에)
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
			// 마이페이지 or 신청화면에서 '일반인증키' 가져오기(=서비스키)
		//sb.append("SaWv7aufqqQ4eySc2FZM%2BL7oYEhYGai2gRMMmHc%2FsI%2BD4mYGpTIaDtX%2FPBDhqt1XRRPdaYveF1Awht%2BEcpKiUA%3D%3D");
		//위에 상수로 빼 놨음
		sb.append("?serviceKey=");
		sb.append(SERVICE_KEY);
		sb.append("&sidoName=");
		sb.append("&returnType=json");
		sb.append(URLEncoder.encode("서울", "UTF-8"));
		
		String url = sb.toString();
		
		System.out.println(url);
		
		// Java코드를 가지고 URL로 요청을 보낼 것
		// HttpURLConnection 객체를 활용해서 API서버로 요청
		// 1. java.net.URL로 객체 생성 => 생성자 호출 시 url값을 인자값으로 전달!!
		URL requestUrl = new URL(url);
		// 2. 생성한 url객체를 가지고 HttpURLConnection객체를 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();	//형이 달라서 오류 뜸. HttpURLConnection는 URLConnection 클래스의 자식 클래스.
		// 3. 요청에 필요한 설정(방식 설정)
		urlConnection.setRequestMethod("GET");
		// 4. API서버와 스트림 연결
			// 순수 자바에서 입력 받기 - inputStream 필요
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));	//BufferedReader : 보조스트림. 기반스트림 없이 홀로 존재할 수 없음
		
		/* 몇 줄일지 모르니 반복문 써야
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		*/
		
		// while문은 안 됨! 띄엄띄엄 나옴
		// 한 줄 문자열 읽고 한 줄 출력하고 읽고 또 다음줄 출력하고 반복
		/*
		while(br.readLine() != null) {
			System.out.println(br.readLine());
		}
		*/
		
		// 나는 한 줄 불러서 null 체크하고 그 줄 출력도 하고 싶다
		//	=> 하나의 값으로 두 번 이상의 작업을 하고 싶다!! => >>>변수<<<말고는 없음
		/* 기초
		while(true) {
			String value = br.readLine();
			if(value != null) {
				System.out.pringln(value);
			} else {
				break;
			}
		}
		 */
		
		/*
		// 심화편
		String responseXml = "";
		while((responseXml = br.readLine()) != null) {
			System.out.println(responseXml);
		}
		*/
		
		String responseJson = br.readLine();
		//System.out.println(responseJson);
		/*
		AirVO air = new AirVO();
		air.setKhaiValue("57");
		*/
		//지금 하는 거 : String같이 생긴 걸 갖다가 VO로 만들어줘야
		
		// 라이브러리
		// JsonObject, JsonArray => JSON => 자바데이터	=> GSON라이브러리
		// + JsonParser
		
		// JSONObject, JSONArray => 자바데이터를 => JSON => JSON라이브러리
		
		// 
	}

}
