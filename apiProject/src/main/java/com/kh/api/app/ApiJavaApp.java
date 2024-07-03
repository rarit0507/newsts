package com.kh.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		sb.append("&returnType=json");
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode("서울", "UTF-8"));
		sb.append("&returnType=json");
		
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
		
		// {"response":{"body":{"totalCount":40,"items":[{"so2Grade":"1","coFlag":null,"khaiValue":"57","so2Value":"0.003","coValue":"0.5","pm10Flag":null,"o3Grade":"1","pm10Value":"11","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"2","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.034","stationName":"정릉로","pm10Grade":"1","o3Value":"0.019"},{"so2Grade":"1","coFlag":null,"khaiValue":"-","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"6","khaiGrade":null,"sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.012","stationName":"도봉구","pm10Grade":null,"o3Value":"0.035"},{"so2Grade":"1","coFlag":null,"khaiValue":"-","so2Value":"0.002","coValue":"0.5","pm10Flag":null,"o3Grade":"1","pm10Value":"3","khaiGrade":null,"sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.005","stationName":"은평구","pm10Grade":"1","o3Value":"0.029"},{"so2Grade":"1","coFlag":null,"khaiValue":"62","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"2","pm10Value":"4","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.007","stationName":"서대문구","pm10Grade":"1","o3Value":"0.044"},{"so2Grade":"1","coFlag":null,"khaiValue":"54","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"4","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.014","stationName":"마포구","pm10Grade":"1","o3Value":"0.033"},{"so2Grade":"1","coFlag":null,"khaiValue":"47","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"1","pm10Value":"5","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.014","stationName":"신촌로","pm10Grade":"1","o3Value":"0.028"},{"so2Grade":"1","coFlag":null,"khaiValue":"51","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"2","pm10Value":"3","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.015","stationName":"강서구","pm10Grade":"1","o3Value":"0.031"},{"so2Grade":"1","coFlag":null,"khaiValue":"50","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"1","pm10Value":"8","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.020","stationName":"공항대로","pm10Grade":"1","o3Value":"0.030"},{"so2Grade":"1","coFlag":null,"khaiValue":"61","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"3","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.010","stationName":"구로구","pm10Grade":"1","o3Value":"0.042"},{"so2Grade":"1","coFlag":null,"khaiValue":"56","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"7","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-02 17:00","coGrade":"1","no2Value":"0.016","stationName":"영등포구","pm10Grade":"1","o3Value":"0.036"}],"pageNo":1,"numOfRows":10},"header":{"resultMsg":"NORMAL_CODE","resultCode":"00"}}}
		/*
		AirVO air = new AirVO();
		air.setKhaiValue("57");
		*/
		//지금 하는 거 : String같이 생긴 걸 갖다가 VO로 만들어줘야
		
		// 라이브러리
		// JsonObject, JsonArray => JSON => 자바데이터	=> GSON라이브러리
		// + JsonParser
		
		// JSONObject, JSONArray => 자바데이터를 => JSON => JSON라이브러리
		
		//JsonObject 타입으로 만들기(파싱Parsing)
		JsonObject jsonObj = JsonParser.parseString(responseJson).getAsJsonObject();		// .getAsJsonObject() : JsonObject라는 타입의 객체를 받아옴
		
		//System.out.println(responseJson);	//String타입
		//System.out.println("-----------------------------------");
		//System.out.println(jsonObj);		//JsonObject타입
		JsonObject responseObj = jsonObj.getAsJsonObject("response");	// response라는 property로 접근 => {} JsonObject
		//System.out.println("-----------------------------------");
		//System.out.println(responseObj);
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");	// body라는 property로 접근 => {} JsonObject
		//System.out.println("-----------------------------------");
		//System.out.println(bodyObj);
		//★ 중요 !!! : 파싱했는데 왜 결과가 똑같지? => 아 이걸 자바에서는 맵으로 받으면 되겠구나!!!
		
		//totalCount속성 객체의 값 '40'을 뭐로 받아오지? => byte, short, int, long
		int totalCount = bodyObj.get("totalCount").getAsInt();		// totalCount라는 property로 접근 => : ㅑㅜㅅ
		//System.out.println(totalCount);
		
		JsonArray items = bodyObj.getAsJsonArray("items");		// items property => : [] JsonArray
		//System.out.println(items);
		
		//배열(items : JsonObject 형태) 내의 값만 뽑아와야 함!!
		// JSON 객체 형태에서 값을 뽑기 : List로!
			JsonObject firstItem = items.get(0).getAsJsonObject();
		//System.out.println(firstItem);
		/*
		 * {"so2Grade":"1","coFlag":null,"khaiValue":"67","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"2","pm10Value":"21","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2024-07-03 10:00","coGrade":"1","no2Value":"0.023","stationName":"정릉로","pm10Grade":"1","o3Value":"0.050"}
		 */
		List<AirVO> list = new ArrayList();		//air를 담을 배열
		
		for(int i = 0; i < items.size(); i++) {
			
			JsonObject item = items.get(i).getAsJsonObject();
			
			AirVO air = new AirVO();	//값을 하나씩 받아와서 VO 안에 넣겠다
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			
			list.add(air);
			//System.out.println(air);
		}
		//몇 개일지는 모르겠으나 List인 건 앎 => size 이용해서 반복문 사용!
		for(AirVO air : list) {
			System.out.println(air);
		}
		
		// 자원 반납
		br.close();
	    urlConnection.disconnect();
	}

}
