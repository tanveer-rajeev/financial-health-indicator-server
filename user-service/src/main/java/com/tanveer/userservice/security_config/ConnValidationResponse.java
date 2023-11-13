package com.tanveer.userservice.security_config;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@ToString
public class ConnValidationResponse {
    private String status;
    private boolean isAuthenticated;
    private String methodType;
    private String username;
    private String token;
    private Collection<GrantedAuthority> authorities;
}