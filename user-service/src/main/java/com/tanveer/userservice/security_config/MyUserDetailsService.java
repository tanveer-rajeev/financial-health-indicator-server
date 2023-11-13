package com.tanveer.userservice.security_config;

import com.tanveer.userservice.Exception.CustomException;
import com.tanveer.userservice.model.User;
import com.tanveer.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhoneNumber) {
        try {

            Optional<User> optionalUser = userRepository.findByEmail(emailOrPhoneNumber);
            if (optionalUser.isEmpty()) {
                throw new CustomException("No user found by given email: " + emailOrPhoneNumber);
            }
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails
                    .User(user.getEmail(), user.getPassword(), true,
                    true, true, true,
                    new ArrayList<>());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
