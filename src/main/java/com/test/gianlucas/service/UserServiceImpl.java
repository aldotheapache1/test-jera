package com.test.gianlucas.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.test.gianlucas.dto.UserRegistrationDto;
import com.test.gianlucas.model.Profile;
import com.test.gianlucas.model.Role;
import com.test.gianlucas.model.User;
import com.test.gianlucas.repository.UserRepository;


@Service
public class UserServiceImpl implements UserInterface{

	private UserRepository userRepository;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void save(UserRegistrationDto registrationDto) {
		Profile profile = new Profile();
		profile.setName(registrationDto.getName());
		User user = new User(registrationDto.getName(), 
				registrationDto.getBirthDate(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		profile.setUser(user);
		
		 userRepository.save(user);
		 profileService.salvar(profile);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
