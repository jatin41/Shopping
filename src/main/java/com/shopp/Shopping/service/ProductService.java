package com.shopp.Shopping.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.shopp.Shopping.model.Cart;
import com.shopp.Shopping.model.Products;
import com.shopp.Shopping.repository.ProductRepository;
import com.shopp.Shopping.util.ImageUtil;
import com.shopp.Shopping.web.dto.ProductsDto;

@Service
public class ProductService {
	@Autowired
	public ProductRepository productRepository;
	
	
	public List<Products> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Products GetById(Long id) {
		return productRepository.findById(id).get();
	}
	
	public Products addNew(MultipartFile image, ProductsDto productsDto) throws IOException {
		byte[] imageData = image.getBytes();
		System.out.println(imageData);
		Products product = new Products();
		product.setName(productsDto.getName());
		product.setInformation(productsDto.getInformation());
		product.setPrice(productsDto.getPrice());
		product.setData(imageData);
		return productRepository.save(product);
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	public Products getByName(String name) {
		return productRepository.getByName(name);	
	}
	
}
