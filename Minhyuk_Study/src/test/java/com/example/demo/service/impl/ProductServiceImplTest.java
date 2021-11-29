package com.example.demo.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.data.dto.ProductDTO;
import com.example.demo.data.entity.ProductEntity;
import com.example.demo.data.handler.impl.ProductDataHandlerImpl;

//SpringBootTest(classese = {productDataHandlerImpl.class, ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {
	
	@MockBean
	ProductDataHandlerImpl productDataHandler;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Test
	public void getProductTest() {
		//given
		Mockito.when(productDataHandler.getProductEntity("123"))
		.thenReturn(new ProductEntity("123", "pen", 2000, 5000));
		
		ProductDTO productDto = productService.getProduct("123");
				
		Assertions.assertEquals(productDto.getProductId(), "123");
		Assertions.assertEquals(productDto.getProductName(), "pen");
		Assertions.assertEquals(productDto.getProductPrice(), 2000);
		Assertions.assertEquals(productDto.getProductStock(), 5000);
		
		verify(productDataHandler).getProductEntity("123");
	}
	
	@Test
	public void saveProductTest() {
		//given
		Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 5000))
		.thenReturn(new ProductEntity("123", "pen", 2000, 5000));
		
		ProductDTO productDto = productService.saveProduct("123", "pen", 2000, 5000);
		
		Assertions.assertEquals(productDto.getProductId(), "123");
		Assertions.assertEquals(productDto.getProductName(), "pen");
		Assertions.assertEquals(productDto.getProductPrice(), 2000);
		Assertions.assertEquals(productDto.getProductStock(), 5000);
	
		verify(productDataHandler).saveProductEntity("123", "pen", 2000, 5000);
	}
}
