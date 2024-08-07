package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

//비즈니스 로직을 처리하는 곳
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	@Override
	public int idCheck(String checkId) {
		return memberRepository.idCheck(sqlSession, checkId);
	}

	private final SqlSessionTemplate sqlSession;
	private final MemberRepository memberRepository;
	
	@Override
	public int returnNum() {
		return 1;
	}

	@Override
	public Member login(Member member) {
		
		// 내가 수행해야 하는 SQL문을 호출
		return memberRepository.login(sqlSession, member);
	}
	
	//dao 호출 , 컨트롤러로 결과 반환
	@Override
	public int insert(Member member) {
		
		return memberRepository.insert(sqlSession,member);
	}

	@Override
	public int update(Member member) {
		return memberRepository.update(sqlSession,member);
	}

	@Override
	public int delete(String userId) {
		return memberRepository.delete(sqlSession, userId);
	}

}
