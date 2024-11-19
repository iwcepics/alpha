package com.example.IWCserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String theme;
    private char level; // A, B, C, or D
    private String answer;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    // Constructors
    public Question() {}

    public Question(String content, String theme, char level, String answer, Test test) {
        this.content = content;
        this.theme = theme;
        this.level = level;
        this.answer = answer;
        this.test = test;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
