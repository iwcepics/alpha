package com.example.IWCserver.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;
    private Date completionDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    // Constructors
    public TestResult() {}

    public TestResult(Double score, Date date, Student student, Test test) {
        this.score = score;
        this.completionDate = date;
        this.student = student;
        this.test = test;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getDate() {
        return completionDate;
    }

    public void setDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

}
