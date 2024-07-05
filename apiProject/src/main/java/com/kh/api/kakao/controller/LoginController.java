package com.kh.api.kakao.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.api.kakao.model.SocialMember;
import com.kh.api.kakao.model.service.KakaoService;

@Controller
public class LoginController {

	@Autowired
	private KakaoService kakaoService;
	
	// 인가 코드 받아오기
	@GetMapping("oauth")
	public String socialLogin(String code, HttpSession session) throws IOException, ParseException {
		
		//System.out.println(code);
		
		// 코드를 이용해 토큰 받아와야 함
		String accessToken = kakaoService.getToken(code);
		session.setAttribute("accessToken", accessToken); 	//로그아웃 때도 access 토큰이 필요하므로 세션에 담아둠
		
		SocialMember sm = kakaoService.getUserInfo(accessToken);
		session.setAttribute("loginUser", sm);
		
		return "redirect:login";
		
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		String accessToken = (String)session.getAttribute("accessToken");
		
		kakaoService.logout(accessToken);
		
		return "redirect:login";
	}
	
}
