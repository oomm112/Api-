package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;

/*	@ResponseEntity?
	Spring Framework에서 제공하는 클래스중 HttpEntity라는 클래스를 상속받아 사용하는 클래스
	사용자의 HttpRequest에 대한 응답 데이터를 포함
	포함하는 클래스?
	1.HttpStatus 2.HttpHeaders 3.HttpBody*/

@RestController
@RequestMapping("api/put-api")
public class PutController {
	
	@PutMapping(value = "/default")
	public String putMethod() {return "기본 입니다!";}
	
	@PutMapping(value = "/member")
	public String putMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + ":" + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	//toString으로 가공되어 값이 전달됨
	@PutMapping(value = "/member1")
	public String putMeberDTO1(@RequestBody MemberDTO memberDTO) {return memberDTO.toString();}
	
	//Json형태로 값이 전달된다. String값보단 Json형태가 더 선호됨!
	@PutMapping(value = "/member2")
	public MemberDTO putMeberDTO2(@RequestBody MemberDTO memberDTO) {return memberDTO;}
	
	
	//HttpStatus 202번인 Accepted로 변경되어 전달되게된다.
	@PutMapping(value = "/member3")
	public ResponseEntity<MemberDTO> putMeberDTO3(@RequestBody MemberDTO memberDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
	}
}
