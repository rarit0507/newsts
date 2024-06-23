package com.kh.spring.notice.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.notice.model.vo.Notice;

@Repository
public class NoticeRepository {

	public int noticeCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.noticeCount");
	}
	
	public List<Notice> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("noticeMapper.findAll", map);
	}
	
	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("noticeMapper.searchCount", map);
	}
	
	public List<Notice> findByConditionAndKeyword(SqlSessionTemplate sqlSession, Map<String, String> map, RowBounds rowbounds) {
		return sqlSession.selectList("noticeMapper.findByConditionAndKeyword", map, rowbounds);
	}
	
	public int insert(SqlSessionTemplate sqlSession, Notice notice) {
		return sqlSession.insert("noticeMapper.insert", notice);
	}

	public Notice findById(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}
}