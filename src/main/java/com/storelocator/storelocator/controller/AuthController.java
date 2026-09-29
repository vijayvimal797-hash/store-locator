package com.storelocator.storelocator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storelocator.storelocator.dto.LoginRequest;
import com.storelocator.storelocator.dto.RegisterRequest;
import com.storelocator.storelocator.model.User;
import com.storelocator.storelocator.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = authService.register(request);
        if (result.equals("Registration successful!")) {
            return ResponseEntity.ok(Map.of("message", result));
        }
        return ResponseEntity.badRequest().body(Map.of("message", result));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        if (user != null) {
            return ResponseEntity.ok(Map.of(
                "message", "Login successful!",
                "userId", user.getId(),
                "username", user.getUsername(),
                "name", user.getName()
            ));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Invalid username or password!"));
    }
}