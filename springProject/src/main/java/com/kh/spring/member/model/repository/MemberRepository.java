package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

// Repository : 저장소
// 영속성 작업 : DB CRUD 작업
// sql문 실행하고 결과 받아오는 것 외의 다른 일은 들어가면안됨.
@Repository
public class MemberRepository {
	
	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}

	public int insert(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.insert",member);
	}

	public int update(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.update",member);
	}

	public int delete(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.delete",userId);
		//탈퇴 번복할 거 대비해서 member.status를 'Y'->'N'으로 바꿔주기만
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
	}
}
