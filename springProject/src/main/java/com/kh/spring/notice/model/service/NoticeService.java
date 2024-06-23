package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {

// 게시글 전체 조회 + 페이징
	
	// 1. 조회된 게시물의 수
	int noticeCount();
	
	// 2. 게시물 목록 조회
	List<Notice> findAll(Map<String, Integer> map);
	
	// 3. 게시글 검색 기능
	int searchCount(Map<String, String> map);
	
	// 4. 검색 목록 조회
	List<Notice> findByConditionAndKeyword(Map<String, String> map, RowBounds rowbounds);
	
	// 5. 게시글 작성
	int insert(Notice notice);
	
	// 6. 게시글 상세보기
	Notice findById(int noticeNo);
	
	// 7. 게시글 삭제
	int delete(int noticeNo);
	
	// 8. 게시글 수정
	int update(Notice notice);
}