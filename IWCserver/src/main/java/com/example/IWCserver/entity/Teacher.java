package com.example.IWCserver.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @Column(unique = true, nullable = false)
    private String email; // Used for teacher login

    // Relationships
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Test> testsCreated;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    // Constructors
    public Teacher() {}

    public Teacher(String name, String email, String password) {
        super(name, password, Role.TEACHER);
        this.email = email;
    }
    
    // Getters and Setters
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Test> getTestsCreated() {
        return testsCreated;
    }

    public void setTestsCreated(List<Test> testsCreated) {
        this.testsCreated = testsCreated;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
