package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/test")
	public String test() {return "Hello World!";}
	
	@PostMapping("log-test")
	public void logTest() {
		LOGGER.trace("Trace Log");
		LOGGER.debug("Debug Log");
		LOGGER.info("Info Log");
		LOGGER.warn("Warn Log");
		LOGGER.error("Error Log");
	}
	
	@PostMapping("/exception")
	public void exceptionTest() throws Exception{
		throw new Exception();
	}
	
	//로컬 ExceptionHandler가  컨트롤러내의 핸들러보다 우선순위가 낮다!!
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		LOGGER.info(e.getMessage());
		LOGGER.info("Controller 내 ExceptionHandler 호출됨");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code","400");
		map.put("message", "Error 발생!");
		
		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}
}
