package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.data.dto.MemberDTO;

public interface RestTemplateService {
	
	public String getMain();
	
	public String getName();
	
	public String getName2();
	
	public ResponseEntity<MemberDTO> postDto();
	
	public ResponseEntity<MemberDTO> addHeader();
}
