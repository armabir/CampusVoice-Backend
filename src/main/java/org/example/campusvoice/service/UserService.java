package org.example.campusvoice.service;

import org.example.campusvoice.model.User;
import org.example.campusvoice.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // --- Role Assignment Logic ---
        String email = user.getEmail();

        if (email.endsWith(".admin@seu.edu.bd")) {
            user.setRole("ADMIN");
        } else if (email.matches("\\d+@seu\\.edu\\.bd")) {
            // Matches digits (student ID) + @seu.edu.bd
            user.setRole("STUDENT");
        } else if (email.endsWith("@seu.edu.bd")) {
            // If it's an @seu.edu.bd email but doesn't fit the ID or Admin pattern
            user.setRole("FACULTY");
        } else {
            throw new RuntimeException("Invalid university email format");
        }
        // -----------------------------

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: User not found");
        }
        userRepository.deleteById(id);
    }
}