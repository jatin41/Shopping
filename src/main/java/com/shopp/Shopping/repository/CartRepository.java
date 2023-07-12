package com.shopp.Shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopp.Shopping.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value = "SELECT c.* FROM Cart c JOIN Register r ON c.user_id = r.id WHERE r.email = ?", nativeQuery = true)
	Cart getCartByUserEmail(String username);
}
