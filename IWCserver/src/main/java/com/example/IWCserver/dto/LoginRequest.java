package com.example.IWCserver.dto;

public class LoginRequest {
    private String identifier; // Email for teachers, ID for students
    private String password;

    // Getters and Setters
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    this.password = password;
    }
}
