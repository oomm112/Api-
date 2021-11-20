package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok이용 해서 getter와 setter, ToString을 자동으로 등록
 * lombok사용시 이클립스에 lombok버전을 따로 다운받아줘야한다!!
*/
@Getter
@Setter
@ToString
public class MemberDTO {
	private String name;
	private String email; 
	private String tel;
}