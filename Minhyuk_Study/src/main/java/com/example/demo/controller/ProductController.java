package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants.ExceptionClass;
import com.example.demo.common.exception.TestException;
import com.example.demo.data.dto.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product-api")
public class ProductController {
	
	private ProductService productService;
	
	//자동으로 연결을 해준다.
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/product/{productId}")
	public ProductDTO getProduct(@PathVariable String productId) {

		ProductDTO productDto = productService.getProduct(productId);

		return productDto;
	}
	
	@PostMapping(value = "/product")	//@Valid 어노테이션이 있을경우 유효성 검사 (DTO에는 Valid어노테이션이 있어야함)
	public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
		String productId = productDTO.getProductId();
		String productName = productDTO.getProductName();
		int productPrice = productDTO.getProductPrice();
		int productStock = productDTO.getProductStock();
		
		return productService.saveProduct(productId,productName,productPrice,productStock);
	}
	
	@DeleteMapping(value = "/product/{productId}")
	public ProductDTO deleteProduct(@PathVariable String productId) {
		return null;
	}
	
	@PostMapping(value = "/product/exception")
	public void exceptionTest() throws TestException{
		throw new TestException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러가 발생하였습니다");
	}
}
