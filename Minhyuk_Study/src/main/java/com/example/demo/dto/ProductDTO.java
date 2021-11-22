package com.example.demo.dto;

import com.example.demo.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
	private String productId;
	private String productName;
	private int productPrice;
	private int productStock;
	
	public ProductEntity toEntity() {
		return ProductEntity.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productStock(productStock)
				.build();
	}
}
