package com.shopp.Shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopp.Shopping.model.Products;
import com.shopp.Shopping.web.dto.ProductsDto;
@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
	
	Products save(ProductsDto productsDto);
	@Query(value= "select * from public.products where name = ?", nativeQuery = true )
	Products getByName(String name);
}
