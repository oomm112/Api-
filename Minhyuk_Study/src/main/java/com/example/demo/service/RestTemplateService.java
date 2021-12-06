package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
	
	public String getMain();
	
	public String getName();
	
	public String getName2();
	
	public ResponseEntity<MemberDTO> postDto();
	
	public ResponseEntity<MemberDTO> addHeader();
}
