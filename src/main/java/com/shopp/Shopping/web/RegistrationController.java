package com.shopp.Shopping.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.shopp.Shopping.service.RegisterService;
import com.shopp.Shopping.service.RegisterServiceImpl;
import com.shopp.Shopping.web.dto.RegistrationDto;

@Controller
//@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	public RegisterService registerService;
	
	@ModelAttribute("user")
	public RegistrationDto registrationDto(){
		return new RegistrationDto();
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		
		return "registration";
	}
	@PostMapping("/registration")
	public String RegisterUserAccount(@ModelAttribute("user") RegistrationDto registrationDto) {
		registerService.save(registrationDto);
		return "redirect:/registration?success";
	}
	@GetMapping("/passwordreset")
	public String ResetPassword() {
		return "passwordreset";
	}
	@PostMapping("/passwordreset")
	public String ResetPassword(@ModelAttribute("user") RegistrationDto registrationDto) {
	registerService.passwordreset(registrationDto);
		System.out.println(registrationDto.getPassword());
		System.out.println(registrationDto.getEmail());
		
		return "redirect:/login";
	}

}
