package com.example.IWCserver.dto;

import com.example.IWCserver.entity.Role;

public class StudentRegistrationDto {

    private Long id; // Generated upon registration
    private String name;
    private char level;
    private String language;
    private String password; // Generated password
    private Role role = Role.STUDENT;
    
    // Make sure all required fields are non-null
    public boolean isValid() {
        return name != null && 
               level != '\0' && 
               language != null && 
               password != null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public char getLevel() {
        return level;
    }
    public void setLevel(char level) {
        this.level = level;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}