package com.example.demo.service.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.data.dto.MemberDTO;
import com.example.demo.service.RestTemplateService;

@Service
public class ResetTemplateServiceImpl implements RestTemplateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetTemplateServiceImpl.class);

	@Override
	public String getMain() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9094")
				.path("/api/server/main")
			 	.encode()	//기본적으로 utf-8로 인코딩됨
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : {}",responseEntity.getStatusCode());
		LOGGER.info("body : {}",responseEntity.getBody());
		
		return responseEntity.getBody();
	}

	@Override
	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9094")
				.path("/api/server/name")
				.queryParam("name", "테스트")
				.encode()	//기본적으로 utf-8로 인코딩됨
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : {}",responseEntity.getStatusCode());
		LOGGER.info("body : {}",responseEntity.getBody());
		
		return responseEntity.getBody();
	}

	@Override
	public String getName2() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9094")
				.path("/api/server/path-variable/{name}")
				.encode()	//기본적으로 utf-8로 인코딩됨
				.build()
				.expand("테스트2")	//복수의 값의 경우 , 를 추가하여 구분한다.
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : {}",responseEntity.getStatusCode());
		LOGGER.info("body : {}",responseEntity.getBody());
		
		return responseEntity.getBody();
	}

	@Override
	public ResponseEntity<MemberDTO> postDto() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9094")
				.path("/api/server/member")
				.queryParam("name", "테스트3")
				.queryParam("email", "test@naver.com")
				.queryParam("organization", "Minhyuk-TestBoot")
				.encode()	//기본적으로 utf-8로 인코딩됨
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("테스트!!!");
		memberDTO.setEmail("aaa@naver.com");
		memberDTO.setOrganization("Minhyuk TestSpringBoot");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);
		
		LOGGER.info("status code : {}",responseEntity.getStatusCode());
		LOGGER.info("body : {}",responseEntity.getBody());
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<MemberDTO> addHeader() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9094")
				.path("/api/server/add-header")
				.encode()	//기본적으로 utf-8로 인코딩됨
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("테스트");
		memberDTO.setEmail("aaa@naver.com");
		memberDTO.setOrganization("Minhyuk TestSpringBoot");
		
		RequestEntity<MemberDTO> requestEntity = RequestEntity
				.post(uri)
				.header("test-header","Minhyuk Spring Boot")
				.body(memberDTO);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);
		
		LOGGER.info("status code : {}",responseEntity.getStatusCode());
		LOGGER.info("body : {}",responseEntity.getBody());
		
		return responseEntity;
	}
	
}
