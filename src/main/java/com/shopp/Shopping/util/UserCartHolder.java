package com.shopp.Shopping.util;

import org.springframework.stereotype.Component;

import com.shopp.Shopping.model.Cart;

@Component
public class UserCartHolder {
private Cart userCart;
    
    public Cart getUserCart() {
        return userCart;
    }
    
    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }
}

