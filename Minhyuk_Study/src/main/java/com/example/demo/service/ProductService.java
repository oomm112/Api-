package com.example.demo.service;

import com.example.demo.dto.ProductDTO;

public interface ProductService {
	//루즈 커플링 : 컨트롤러와 서비스가 결합되는. 각 객체간 의존성 줄여주는 역할
	ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);
	
	ProductDTO getProduct(String productId);
}
