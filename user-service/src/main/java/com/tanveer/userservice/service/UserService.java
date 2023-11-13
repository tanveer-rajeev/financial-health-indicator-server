package com.tanveer.userservice.service;

import com.tanveer.userservice.Exception.CustomException;
import com.tanveer.userservice.dto.UserRequestDto;
import com.tanveer.userservice.dto.UserResponseDto;
import com.tanveer.userservice.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User createUser(User user);

    UserResponseDto login(UserRequestDto user) throws CustomException;
}
