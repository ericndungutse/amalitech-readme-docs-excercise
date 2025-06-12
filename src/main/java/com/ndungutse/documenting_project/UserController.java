package com.ndungutse.documenting_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing users
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    // In-memory list to store users
    private final List<UserModel> users = new ArrayList<>();

    /**
     * Constructor that initializes with some sample users
     */
    public UserController() {
        // Add some sample users
        users.add(new UserModel("admin", "admin123"));
        users.add(new UserModel("user1", "password1"));
        users.add(new UserModel("user2", "password2"));
    }

    /**
     * Login endpoint to authenticate a user
     * 
     * @param loginRequest the user credentials for authentication
     * @return 200 OK with user details if authentication is successful, 401 Unauthorized if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        for (UserModel user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Create a copy of the user without the password for security
                UserModel authenticatedUser = new UserModel(user.getUsername(), "[PROTECTED]");
                return ResponseEntity.ok(authenticatedUser);
            }
        }

        return ResponseEntity.status(401).build(); // Unauthorized
    }
}
