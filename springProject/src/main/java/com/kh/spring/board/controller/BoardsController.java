package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//메인에서 들어오는 요청들 처리 => 다 AJAX로 할 것임
// 이 컨트롤러는 /boards로 시작하는 요청이 들어오면 처리를 해줄 컨트롤러

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/boards", produces="application/json; charset=UTF-8")
// /boards로 들어오는 요청 다 받고, json으로 변환할것(ajax(JS사용)때문에)
public class BoardsController {

	/*
	 * ★ REST 방식
	 * Mapping값은 notice로 통일
	 * 
	 * C : INSERT	==>		@PostMapping
	 * R : SELECT	==>		@GetMapping
	 * U : UPDATE	==>		@PutMapping
	 * D : DELETE	==>		@DeleteMapping
	 */
	private final BoardService boardService;
	
	@GetMapping
	public List<Board> findTopFiveBoard() {
		return boardService.findTopFiveBoard();
	}
	
	@GetMapping("/{boardNo}")
	public Board findByBoardNo(@PathVariable int boardNo) {	// 넘어온 PK 받으려고 @PathVariable) 붙임
		log.info("넘어온 PK : {}", boardNo);
		return boardService.findById(boardNo);
	}
}
