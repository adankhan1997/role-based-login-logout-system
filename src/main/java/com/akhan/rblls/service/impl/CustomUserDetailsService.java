package com.akhan.rblls.service.impl;

import com.akhan.rblls.entity.User;
import com.akhan.rblls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // searching the user's email(username) from DB.
        User user = userRepository.findByEmail(email);

        // throwing the exception if user not available
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        // converting the user's role into spring security's authority format
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        // returning the internal object of spring security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
