package com.test.gianlucas.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.test.gianlucas.dto.UserRegistrationDto;

public interface UserInterface extends UserDetailsService{
	void save(UserRegistrationDto registrationDto);
}
