package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.ProductEntity;

//JPARepository 상속 <레포지토리가 사용할 엔티티, 프라이머리키>
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

}
