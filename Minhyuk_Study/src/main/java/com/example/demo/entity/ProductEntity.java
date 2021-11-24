package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product")	//Entity기반 db에 자동으로 테이블을 생성 (즉, 이름은 테이블 이름)
public class ProductEntity {

	@Id		//데이터베이스는 한테이블당 하나의 pk=primary key가 필요하다.
	String productId;
	String productName;
	Integer productPrice;
	Integer productStock;

	public ProductDTO toDto(){
		return ProductDTO.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productStock(productStock)
				.build();
	}
}
