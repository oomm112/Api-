package com.example.demo.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//테스트를 하기위해서는 .java 패키지들과 경로를 맞춰주어야한다.
public class TestLifeCycle {

	@BeforeAll
	static void beforeAll() {
		System.out.println("## BeforeAll Annotation 호출 ##");
		System.out.println();
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("## afterAll Annotation 호출 ##");
		System.out.println();
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("## BeforeEach Annotation 호출 ##");
		System.out.println();
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("## AfterEach Annotation 호출 ##");
		System.out.println();
	}
	
	@Test
	void test1() {
		System.out.println("## test1 시작 ##");
		System.out.println();
	}
	
	@Test
	@DisplayName("Test case 2!!!")
	void test2() {
		System.out.println("## Test2 시작 ##");
		System.out.println();
	}
	
	@Test
	@Disabled //Disabled Annotation : 테스트를 실행하지 않게 설정하는 어노테이션
	void test3() {
		System.out.println("## test3 시작 ##");
		System.out.println();
	}
}
