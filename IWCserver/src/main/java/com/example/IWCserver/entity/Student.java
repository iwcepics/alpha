package com.example.IWCserver.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    private String level; //A, B, C, or D
    private String language;

    // Relationships
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<TestResult> testResults;

    // Constructors
    public Student() {}

    public Student(String name, String password, String level, String language) {
        super(name, password, Role.STUDENT);
        this.level = level;
        this.language = language;
    }

    // Getters and Setters
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }
}
