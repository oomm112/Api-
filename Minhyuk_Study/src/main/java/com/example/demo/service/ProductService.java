package com.example.demo.service;

import com.example.demo.dto.ProductDTO;

public interface ProductService {
	ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);
	
	ProductDTO getProduct(String productId);
}
