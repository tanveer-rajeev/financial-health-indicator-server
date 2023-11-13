package com.tanveer.userservice.controller;

import com.tanveer.userservice.Exception.CustomException;
import com.tanveer.userservice.dto.ApiResponse;
import com.tanveer.userservice.dto.UserRequestDto;
import com.tanveer.userservice.model.User;
import com.tanveer.userservice.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user){
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success("SUCCESS")
                        .message("User create successfully")
                        .data(userService.createUser(user))
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@Valid @RequestBody UserRequestDto request) throws CustomException {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .success("SUCCESS")
                        .message("LOGIN_SUCCESS")
                        .data(userService.login(request))
                        .build()
        );
    }
}
