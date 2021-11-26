package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.example.demo.entity.ProductEntity;
import com.sun.istack.NotNull;

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
	@NotNull
	/* @Size(min = 3, max = 30) */
	private String productId;

	@NotNull
	private String productName;
	
	@NotNull
	@Min(value = 500)
	@Max(value = 3000000)
	private int productPrice;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 1000000)
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
