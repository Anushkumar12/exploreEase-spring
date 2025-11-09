package com.ExploreEase.ExploreEase.controller;

import com.ExploreEase.ExploreEase.dto.LoginRequest;
import com.ExploreEase.ExploreEase.dto.JwtResponse;
import com.ExploreEase.ExploreEase.entity.User;
import com.ExploreEase.ExploreEase.service.AuthService;
import com.ExploreEase.ExploreEase.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully: " + newUser.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
        String token = userService.login(req.getEmail(), req.getPassword());
        User user = userService.findByEmail(req.getEmail())
                               .orElseThrow(() -> new RuntimeException("User not found"));

        String role = user.getRoles().stream()
                          .findFirst()
                          .map(r -> r.getName())
                          .orElse("ROLE_USER");

        return ResponseEntity.ok(new JwtResponse(token, "Bearer", req.getEmail(), role));
    }
}
