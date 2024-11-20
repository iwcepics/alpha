package com.example.IWCserver.dto;

public class StudentRegistrationDto {
    private String name;
    private String language;
    private String level;
    private String password;

    // Default Constructor
    public StudentRegistrationDto() {}
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}