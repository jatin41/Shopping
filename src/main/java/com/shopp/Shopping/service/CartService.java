package com.shopp.Shopping.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopp.Shopping.model.Cart;
import com.shopp.Shopping.model.Products;
import com.shopp.Shopping.repository.CartRepository;
import com.shopp.Shopping.util.UserCartHolder;
import com.shopp.Shopping.web.MainController;
import com.shopp.Shopping.web.dto.ProductsDto;
@Service
public class CartService {

	@Autowired
	public CartRepository cartRepository;
	@Autowired
	public UserCartHolder userCartHolder;
	
	public Cart save(Cart cart) {
		userCartHolder.setUserCart(cart);
		return cartRepository.save(userCartHolder.getUserCart());
		}
	public List<Cart> findAll(){
		return cartRepository.findAll();
	}
//	public Cart getByUser() {
//		return cartRepository.getCartByUserId();
//	}
}
