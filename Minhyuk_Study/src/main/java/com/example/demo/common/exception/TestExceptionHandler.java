package com.example.demo.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class TestExceptionHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(TestExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		LOGGER.info(e.getMessage());
		LOGGER.info("Advice 내 ExceptionHandler 호출됨");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code","400");
		map.put("message", "Error 발생!");
		
		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}
	
	@ExceptionHandler(value = TestException.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(TestException e){
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error type", e.getHttpStatusType());
		map.put("code",Integer.toString(e.getHttpStatusCode()));
		map.put("message", e.getMessage());
		
		return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
	}
}
