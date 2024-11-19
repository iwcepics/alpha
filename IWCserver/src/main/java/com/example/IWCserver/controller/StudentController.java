package com.example.IWCserver.controller;

import com.example.IWCserver.service.StudentService;
import com.example.IWCserver.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Endpoints related to student operations
 */
//@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoints for student operations
    @GetMapping("/all") // Get all students
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
}
