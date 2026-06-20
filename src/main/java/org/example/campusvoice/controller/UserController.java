package org.example.campusvoice.controller;

import org.example.campusvoice.dto.LoginRequest;
import org.example.campusvoice.model.User;
import org.example.campusvoice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        System.out.println("incoming");
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User fetchUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public User fetchUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.getUserByEmail(loginRequest.getEmail());

            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user); // Login successful
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}