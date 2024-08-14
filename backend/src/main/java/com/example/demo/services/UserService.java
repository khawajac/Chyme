package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        // Validate the user object if necessary
        validateUser(user);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUsername(Long id, String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User updateEmail(Long id, String email) {
        if (!StringUtils.hasText(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User updatePassword(Long id, String password) {
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Password cannot be empty or null.");
        }
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!StringUtils.hasText(user.getUsername())) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (!StringUtils.hasText(user.getEmail()) || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        // Add additional validation logic as needed
    }
}
