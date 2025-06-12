package com.ndungutse.documenting_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLoginSuccess() {
        // Create login request with valid credentials
        UserModel loginRequest = new UserModel("admin", "admin123");
        
        // Send login request
        ResponseEntity<UserModel> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/users/login",
                loginRequest,
                UserModel.class);
        
        // Assert successful login
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("admin", response.getBody().getUsername());
        assertEquals("[PROTECTED]", response.getBody().getPassword());
    }
    
    @Test
    public void testLoginFailureInvalidUsername() {
        // Create login request with invalid username
        UserModel loginRequest = new UserModel("nonexistent", "password");
        
        // Send login request
        ResponseEntity<UserModel> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/users/login",
                loginRequest,
                UserModel.class);
        
        // Assert unauthorized status
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    
    @Test
    public void testLoginFailureInvalidPassword() {
        // Create login request with valid username but invalid password
        UserModel loginRequest = new UserModel("admin", "wrongpassword");
        
        // Send login request
        ResponseEntity<UserModel> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/users/login",
                loginRequest,
                UserModel.class);
        
        // Assert unauthorized status
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}