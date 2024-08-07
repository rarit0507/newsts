package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok 사용 시 주의사항!!
 * 
 * - 시작글자가 외자인 필드명은 XXXX
 * ex). 올바른 표기 : productName / X : pName (컨벤션 위반)
 * - pName   -->  getPName()  (X)	//후에 EL 사용 시 메서드가 다르게 정의됨
 * - userName  -->  getUserName()  (O)
 * 
 * => jsp에서 EL표기법을 이용할 때 내부적으로 getter 사용 방법 !
 * 
 * ${pName} == getpName()
 * 
 * 필드명 작성 시 최소 소문자 두 글자 이상으로 시작할 것 !!
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;	//LocalDateTime형도 있음. String으로 굳이 선언할 필요가??
	private Date modifyDate;
	private String status;
	
}
