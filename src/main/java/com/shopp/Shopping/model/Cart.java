package com.shopp.Shopping.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private Register user;
    
	@ManyToMany
    @JoinTable(
        name = "cart_product",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Products> products = new HashSet<>();
	private Integer total;
	public double totalPrice;

	public Cart(Long id, Register user, Set<Products> products, Integer total, double totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
		this.total = total;
		this.totalPrice = totalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Register getUser() {
		return user;
	}

	public void setUser(Register user) {
		this.user = user;
	}

	public Set<Products> getProducts() {
		return products;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}

	public Cart(Long id, Register user, Set<Products> products) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Cart(Long id, Register user, Set<Products> products, Integer total) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
		this.total = total;
	}



	
}
