package com.example.demo.dao;

import com.example.demo.entity.ProductEntity;

public interface ProductDAO {
	ProductEntity saveProduct(ProductEntity productEntity);
	
	ProductEntity getProduct(String productId);
}
