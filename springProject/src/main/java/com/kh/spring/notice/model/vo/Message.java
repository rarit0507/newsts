package com.kh.spring.notice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Message {

	//오류코드나 뭐 그런 거 메시지 보내는 필드
	private String message;
	private Object data;
}
