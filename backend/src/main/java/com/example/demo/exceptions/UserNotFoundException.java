package com.example.demo.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
