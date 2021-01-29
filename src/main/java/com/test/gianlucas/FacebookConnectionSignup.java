package com.test.gianlucas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import com.test.gianlucas.model.Profile;
import com.test.gianlucas.model.User;
import com.test.gianlucas.repository.ProfileRepository;
import com.test.gianlucas.repository.UserRepository;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

	
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
   

    @Override
    public String execute(Connection<?> connection) {
        User user = new User(); 
        Profile profile = new Profile();
        user.setEmail(connection.getDisplayName());
        profile.setName(connection.getDisplayName());
        if(userRepository.findByEmail(connection.getDisplayName()) == null) {
	        user.setName(connection.getDisplayName());
	        user.setPassword(connection.getDisplayName());
	        profile.setUser(user);
	        userRepository.save(user);
	        profileRepository.save(profile);
        }
	       
        return user.getEmail();
    }
}