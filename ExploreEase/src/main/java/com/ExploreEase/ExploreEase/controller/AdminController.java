package com.ExploreEase.ExploreEase.controller;

import com.ExploreEase.ExploreEase.dto.UserDTO;
import com.ExploreEase.ExploreEase.service.UserService;
import com.ExploreEase.ExploreEase.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    // ✅ Get all registered users
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers().stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRoles().stream()
                                .map(role -> role.getName())
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<String> getUserProfile() {
        return ResponseEntity.ok("Access granted! Welcome, authenticated user 🎉");
    }

    // ✅ Delete a user by ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // ✅ Admin dashboard stats
    @GetMapping("/stats")
    public ResponseEntity<String> getStats() {
        long totalUsers = userService.countUsers();
        long totalTours = tourService.countTours();

        return ResponseEntity.ok(
                String.format("System Stats → Total Users: %d, Total Tours: %d", totalUsers, totalTours)
        );
    }
}
