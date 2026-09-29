package com.storelocator.storelocator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storelocator.storelocator.dto.LoginRequest;
import com.storelocator.storelocator.dto.RegisterRequest;
import com.storelocator.storelocator.model.User;
import com.storelocator.storelocator.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists!";
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return "Registration successful!";
    }

    public User login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return user.get();
        }
        return null;
    }
}