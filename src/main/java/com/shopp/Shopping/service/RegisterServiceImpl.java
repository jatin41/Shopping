package com.shopp.Shopping.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopp.Shopping.model.Register;
import com.shopp.Shopping.model.Role;
import com.shopp.Shopping.repository.RegisterRepository;
import com.shopp.Shopping.web.dto.RegistrationDto;
@Service

public class RegisterServiceImpl implements RegisterService{
	 private final RegisterRepository registerRepository;
	   
	    private PasswordEncoder passwordEncoder;

		public RegisterServiceImpl(RegisterRepository registerRepository,PasswordEncoder passwordEncoder) {
	        this.registerRepository = registerRepository;
	        this.passwordEncoder=passwordEncoder;
	    }

	
	@Override
	public Register save(RegistrationDto registrationDto) {
		Register register = new Register(null, registrationDto.getFirstName(),registrationDto.getLastName(), registrationDto.getEmail(),registrationDto.getPhoneno(), passwordEncoder.encode(registrationDto.getPassword()),Arrays.asList(new Role("ROLE_REGISTER")));
		return registerRepository.save(register); 
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Register user = registerRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	@Override
	public Register passwordreset(RegistrationDto registrationDto) {
		
		Register user=registerRepository.findByEmail(registrationDto.getEmail());
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		registerRepository.save(user);
		return user;
	}


	@Override
	public Register getUserById(Long id) {
		// TODO Auto-generated method stub
		return registerRepository.findById(id).get();
	}


	@Override
	public Register getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return registerRepository.findByEmail(email);
	}

}
