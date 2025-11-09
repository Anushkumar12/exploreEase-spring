package com.ExploreEase.ExploreEase.dto;

public class JwtResponse {
    private String token;
    private String type;
    private String email;
    private String role;

    public JwtResponse(String token, String type, String email, String role) {
        this.token = token;
        this.type = type;
        this.email = email;
        this.role = role;
    }

    // Getters and setters
    public String getToken() { return token; }
    public String getType() { return type; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
