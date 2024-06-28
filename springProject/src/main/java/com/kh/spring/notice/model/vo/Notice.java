package com.kh.spring.notice.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private Date createDate;
}
