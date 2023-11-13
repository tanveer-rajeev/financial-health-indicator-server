package com.tanveer.userservice.security_config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/validateToken")
public class ConnectionValidationResourceController {

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ConnValidationResponse> validateGet(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String token = (String) request.getAttribute("jwt");
        Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) request.getAttribute("authorities");
        return ResponseEntity.ok(ConnValidationResponse.builder().status("OK")
                .methodType(HttpMethod.GET.name())
                .username(username)
                .token(token)
                .authorities(grantedAuthorities)
                .isAuthenticated(true).build());
    }

}
