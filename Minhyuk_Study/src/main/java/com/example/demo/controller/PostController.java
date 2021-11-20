package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;

@RestController
@RequestMapping("/api/post-api")
//리소스를 추가하기 위해 사용되는 API
//@PostMapping : POST API를 제작하기 위해 사용되는 어노테이션
//일반적으로 추가하고자 하는 Resource를 http body에 추가하여 서버에 요청
public class PostController {
	
	@PostMapping(value = "/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + ":" + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	//1. DTO사용
	
	//key와 value가 정해져있지만, 받아야할 파라미터가 많을 경우 DTO 객체를 사용한 방식
	//@RequestBody를 사용하지 않으면 null값이 받아지는 현상이 발생할수도 있다.
	@PostMapping(value = "/member2")
	public String postMemberDto(@RequestBody MemberDTO memberDTO) {
		return memberDTO.toString();
	}
	
	
}
