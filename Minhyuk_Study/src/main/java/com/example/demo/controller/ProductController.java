package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product-api")
public class ProductController {
	
	private final Logger PRODUCT_LOGGER = LoggerFactory.getLogger(TestController.class);
	private ProductService productService;
	
	//자동으로 연결을 해준다.
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/product/{productId}")
	public ProductDTO getProduct(@PathVariable String productId) {
		long startTime = System.currentTimeMillis();
		PRODUCT_LOGGER.info("[ProductController] perform {} of Study API","getProduct");
		
		ProductDTO productDto = productService.getProduct(productId);
		
		PRODUCT_LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms"
				,productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));
		
		return productDto;
	}
	
	@PostMapping(value = "/product")
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
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
}
