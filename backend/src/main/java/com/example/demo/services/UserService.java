package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
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
        validateUser(user);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUsername(Long id, String username) {
        validateUsername(username);
        User user = getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User updateEmail(Long id, String email) {
        validateEmail(email);
        User user = getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User updatePassword(Long id, String password) {
        validatePassword(password);
        User user = getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        validateUsername(user.getUsername());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    private void validateUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
    }

    private void validatePassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Password cannot be empty or null.");
        }
    }
}