package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get-api")
public class GetController {
	
	//@RequestMapping(value = "/hello", method = RequestMethod.GET)	//고전적인 방법
	@GetMapping(value = "/hello")		//별도 파라미터 없이 GET API를 호출하는 경우 사용되는 방법
	public String hello() {
		return "Hello World";
	}
	
	
	//1.@PathVariable

	//Get형식의 요청에서 파라미터를 전달하기위해 url에 값을 담아 요청하는 방법
	//아래 방식은 @GetMapping에서 사용된 {변수}의 이름과 메소드의 매개변수와 일치시켜야 함!!
	@GetMapping(value = "/variable1/{variable}")	
	public String getVar(@PathVariable String variable) {
		return variable;
	}

	
	//위와 같은 방식이나 변수의 이름을 매개변수와 다를경우에 사용하는 방법이다.
	@GetMapping(value = "/variable2/{variable}")	
	public String getVar2(@PathVariable("variable") String var) {
		return var;
	}

	//2.@RequestParam
	
	//Get형식의 요청에서 쿼리 문쟈열을 전달하기 위해 사용되는 방법
	//'?'를 기준으로 우측에 {키} = {값}의 형태로 전달되며, 복수형태로 전달될경우 &를 사용한다.
	
	//ex) http://localhost:9092/api/v1/get-api/request1?name=flature&email=think@gmail.com&tel=010-1234-1234
	@GetMapping(value = "/request1")
	public String getRequestParam1(@RequestParam String email, @RequestParam String name, @RequestParam String tel) {
		return name + " " + tel + " " + email;
	}
	

	//Get형식의 요청에서 쿼리 문자열을 전달하기 위해 사용되는 방법
	//아래 예시 코드는 어떤 요청 값이 들어올지 모를 경우 사용하는 방식
	
	//ex) http://localhost:9092/api/v1/get-api/request1?name=flature&email=think@gmail.com&tel=010-1234-1234
	@GetMapping(value = "/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) {
		StringBuilder sb = new StringBuilder();
		
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + ":" + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	//3.DTO사용
	
	//GET형식의 요청에서 쿼리 문자열을 전달하기 위해 사용되는 방법
	//key와 value가 정해져있지만, 받아야할 파라미터가 많을 경우 DTO객체를 사용한 방식
	@GetMapping(value = "/request3")
	public String getRequestParam3(MemberDTO memberDTO) {
		return memberDTO.toString();	
	}
}
