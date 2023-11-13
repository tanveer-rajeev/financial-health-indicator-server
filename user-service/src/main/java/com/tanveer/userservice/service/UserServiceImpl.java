package com.tanveer.userservice.service;

import com.tanveer.userservice.Exception.CustomException;
import com.tanveer.userservice.dto.UserRequestDto;
import com.tanveer.userservice.dto.UserResponseDto;
import com.tanveer.userservice.model.User;
import com.tanveer.userservice.repository.UserRepository;
import com.tanveer.userservice.security_config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public User createUser(User user) {
        User userBuilder = User.builder().email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(userBuilder);
        return userBuilder;
    }

    @Override
    public UserResponseDto login(UserRequestDto request) throws CustomException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new CustomException("User not found by given email"));

        var jwtToken = jwtService.generateToken(userDetails);

        return UserResponseDto.builder()
                .accessToken(jwtToken)
                .email(user.getEmail())
                .userId(user.getId())
                .build();
    }
}
