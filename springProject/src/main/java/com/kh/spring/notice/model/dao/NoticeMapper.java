package com.kh.spring.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {
	
	//sqlSession 생략 가능, Service단과 동일
	//다만 추가 작업 필요
	
	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int delete(int noticeNo);
}
