package com.test.gianlucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.gianlucas.dto.UserRegistrationDto;
import com.test.gianlucas.service.UserInterface;
import com.test.gianlucas.service.UserService;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserInterface userInterface;
	
	@Autowired
	private UserService userService;

	public UserRegistrationController(UserInterface userInterface) {
		super();
		this.userInterface = userInterface;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		//registrationDto.getEmail()
		if(userService.findByEmail(registrationDto.getEmail()) == null) {
			userInterface.save(registrationDto);
		}
		else {
			return "redirect:/registration?error";
		}
		return "redirect:/registration?success";
	}
}
