package com.example.demo.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebMvcTest(ProductController.class)
//@AutoConfigureWebMvc //이 어노테이션을 통해 MockMvc를 Builder없이 주입받을수 있다.
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//productController에서 잡고있는 Bean객체에 대해 Mock형태의 객체를 생성해준다.
	@MockBean
	ProductServiceImpl productServiceImpl;
	
	@Test
	@DisplayName("Product 데이터 가져오기 테스트")
	void getProductTest() throws Exception{
		
		//given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
		given(productServiceImpl.getProduct("12345")).willReturn(new ProductDTO("12345", "pen", 5000, 2000));
		
		String productId = "12345";
		
		//andExcept : 기대하는 값이 나왔는지 체크해 볼수있는 메소드
		mockMvc.perform(get("/api/product-api/product/" + productId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId").exists()) //json path의 depth가 깊어지면 .을 추가하여 탐색 가능.
		.andExpect(jsonPath("$.productName").exists())
		.andExpect(jsonPath("$.productPrice").exists())
		.andExpect(jsonPath("$.productStock").exists())
		.andDo(print());
		
		//verify : 해당 객체의 메소드가 실행되었는지 체크해줌
		verify(productServiceImpl).getProduct("12345");
	}
	
	@Test
	@DisplayName("Product 데이터 생성 테스트")
	void createProductTest() throws Exception{
		//Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어 줘야한다.
		given(productServiceImpl.saveProduct("12345", "pen", 5000, 2000)).willReturn(
				new ProductDTO("12345", "pen", 5000, 2000));
		
		ProductDTO productDto = ProductDTO.builder().productId("12345").productName("pen").productPrice(5000).productStock(2000).build();
		Gson gson = new Gson();
		String content = gson.toJson(productDto);
		
		//아래 코드로 JSON형태 변경 작업 대체가능
		String json = new ObjectMapper().writeValueAsString(productDto);
		
		mockMvc.perform(post("/api/product-api/product")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId").exists()) //json path의 depth가 깊어지면 .을 추가하여 탐색 가능.
		.andExpect(jsonPath("$.productName").exists())
		.andExpect(jsonPath("$.productPrice").exists())
		.andExpect(jsonPath("$.productStock").exists())
		.andDo(print());
		
		//verify : 해당 객체의 메소드가 실행되었는지 체크해줌
		verify(productServiceImpl).saveProduct("12345", "pen", 5000, 2000);
	}
}
