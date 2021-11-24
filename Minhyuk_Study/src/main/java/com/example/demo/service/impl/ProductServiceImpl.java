package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.ProductEntity;
import com.example.demo.handler.ProductDataHandler;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	//옵션사항 데이터가 핸들러가 필요한지 여부에따라서
	ProductDataHandler productDataHandler;
	
	@Autowired
	public ProductServiceImpl(ProductDataHandler productDataHandler) {
		this.productDataHandler = productDataHandler;
	}

	@Override
	public ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock) {
		ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
		
		ProductDTO productDTO = new ProductDTO(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductPrice(),
				productEntity.getProductStock());
		
		return productDTO;
	}

	@Override
	public ProductDTO getProduct(String productId) {
		ProductEntity productEntity = productDataHandler.getProductEntity(productId);
		
		ProductDTO productDTO = new ProductDTO(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductPrice(),
				productEntity.getProductStock());
		
		return productDTO;
	}

}
