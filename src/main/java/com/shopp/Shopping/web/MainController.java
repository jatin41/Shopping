package com.shopp.Shopping.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopp.Shopping.model.Cart;
import com.shopp.Shopping.model.Products;
import com.shopp.Shopping.model.Register;
import com.shopp.Shopping.repository.CartRepository;
import com.shopp.Shopping.service.ProductService;
import com.shopp.Shopping.service.RegisterService;
import com.shopp.Shopping.util.UserCartHolder;
import com.shopp.Shopping.web.dto.RegistrationDto;

@Controller
public class MainController {
	@Autowired
	public RegisterService registerService;
	@Autowired
	public CartRepository cartRepository;
	@Autowired
	public ProductService productService;
	
	@GetMapping("/login")
	public String login() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
//		    UserDetails register = registerService.loadUserByUsername(username);
		    System.out.println(username);
		    return "login";
		
	}
	@GetMapping("/welcome")
	public String string() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
//	    UserDetails register = registerService.loadUserByUsername(username);
	    System.out.println(username);
	    
	   
	    if (cartRepository.getCartByUserEmail(username) == null) {
	    
	    Cart cart = new Cart();
	    cart.setUser(registerService.getUserByEmail(username));
	    cartRepository.save(cart);
	    	
	    }
		return "welcome";
	}
//	@GetMapping("/product")
//	public ResponseEntity<List<Products>>  getAll(){
//		return ResponseEntity.ok(productService.getAllProducts());
//}
//	
	 @GetMapping("/username")
	    @ResponseBody
	    public String currentUserName(Authentication authentication) {
	     System.out.println(authentication.getName()); 
		 return authentication.getName();
	    }
	 

}


