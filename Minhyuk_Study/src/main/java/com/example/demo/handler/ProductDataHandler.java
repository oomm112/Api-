package com.example.demo.handler;

import com.example.demo.entity.ProductEntity;

public interface ProductDataHandler {
	ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);
	
	ProductEntity getProductEntity(String productId);
}
