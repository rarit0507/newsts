package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.repository.NoticeRepository;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
	private final SqlSessionTemplate sqlSession;
	
	@Override
	public int noticeCount() {
		return noticeRepository.noticeCount(sqlSession);
	}

	@Override
	public List<Notice> findAll(Map<String, Integer> map) {
		return noticeRepository.findAll(sqlSession, map);
	}

	@Override
	public int searchCount(Map<String, String> map) {
		return noticeRepository.searchCount(sqlSession, map);
	}

	@Override
	public List<Notice> findByConditionAndKeyword(Map<String, String> map, RowBounds rowbounds) {
		return noticeRepository.findByConditionAndKeyword(sqlSession, map, rowbounds);
	}

	@Override
	public int insert(Notice notice) {
		return noticeRepository.insert(sqlSession, notice);
	}

	@Override
	public Notice findById(int noticeNo) {
		return noticeRepository.findById(noticeNo);
	}

	@Override
	public int delete(int noticeNo) {
		return noticeRepository.delete(noticeNo);
	}

	@Override
	public int update(Notice notice) {
		return noticeRepository.update();
	}

}