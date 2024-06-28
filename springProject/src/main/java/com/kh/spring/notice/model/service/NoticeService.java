package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {

	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int delete(int noticeNo);
}
