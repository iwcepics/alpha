package com.example.IWCserver.controller;

import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints related to teacher operations
 */
//@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Endpoints for teacher operations in TeacherService
    @GetMapping("/all") // Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
