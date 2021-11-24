package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
