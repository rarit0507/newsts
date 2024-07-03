package com.kh.api.pollution.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pollution")
public class AirController {
	
	public static final String SERVICE_KEY = "SaWv7aufqqQ4eySc2FZM%2BL7oYEhYGai2gRMMmHc%2FsI%2BD4mYGpTIaDtX%2FPBDhqt1XRRPdaYveF1Awht%2BEcpKiUA%3D%3D";

	@GetMapping(produces="application/json; charset=UTF-8")
	public String airPollution(String sidoName) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=");
		sb.append(SERVICE_KEY);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode(sidoName, "UTF-8"));
		sb.append("&returnType=json");
		
		String url = sb.toString();
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();	//형이 달라서 오류 뜸. HttpURLConnection는 URLConnection 클래스의 자식 클래스.
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));	//BufferedReader : 보조스트림. 기반스트림 없이 홀로 존재할 수 없음

		String responseData = br.readLine();
		
		br.close();
		urlConnection.disconnect();
		
		return responseData;
	}
	
	// 문자열 데이터로 받아오기
	@GetMapping(value="/xml", produces="text/html; charset=UTF-8")
	public String xmlPollution(String sidoName) throws IOException {

		StringBuilder sb = new StringBuilder();
		
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=");
		sb.append(SERVICE_KEY);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode(sidoName, "UTF-8"));
		sb.append("&returnType=xml");
		
		String url = sb.toString();
		
		//System.out.println(url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();	//형이 달라서 오류 뜸. HttpURLConnection는 URLConnection 클래스의 자식 클래스.
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));	//BufferedReader : 보조스트림. 기반스트림 없이 홀로 존재할 수 없음
		
		String responseData = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseData += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		//System.out.println(responseData);
		return responseData;
	}
	
}
