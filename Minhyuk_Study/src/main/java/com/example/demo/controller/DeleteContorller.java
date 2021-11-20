package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*	DeleteAPI의 경우 일반적으로 @PathVariable을 통해 리소스 ID등을 받아서 처리한다!*/
@RestController
@RequestMapping("/api/delete-api")
public class DeleteContorller {
	
	@DeleteMapping(value = "/delete/{variable}")
	public String deleteVariable(@PathVariable String variable) {return variable;}
}
