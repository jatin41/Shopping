package com.shopp.Shopping.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shopp.Shopping.model.Register;
import com.shopp.Shopping.web.dto.RegistrationDto;

public interface RegisterService extends UserDetailsService {
	Register save(RegistrationDto registrationDto);
	Register passwordreset(RegistrationDto registrationDto);
	Register getUserById(Long id);
	Register getUserByEmail(String email);
}
