package com.tanveer.userservice;

import com.tanveer.userservice.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestH2Repository testH2Repository;

    private String baseUrl = "http://localhost";
    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/auth/register");
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddUser() {
        User user = User.builder().email("test@gmail.com").password("123").build();
        User response = restTemplate.postForObject(baseUrl, user, User.class);
        assertEquals("test@gmail.com", response.getEmail());
    }
}
