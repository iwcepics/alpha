package com.example.IWCserver.controller;

import com.example.IWCserver.dto.StudentRegistrationDto;
import com.example.IWCserver.dto.TeacherRegistrationDto;
import com.example.IWCserver.entity.Student;
import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.service.TeacherService;
import com.example.IWCserver.service.StudentService;


//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
//import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Map;

/**
 * Endpoints related to authentication
 */
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

@RestController
@RequestMapping("/api/auth")
@EnableMethodSecurity
public class AuthController {

    //private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //@Autowired
    //private AuthenticationManager authenticationManager;
    
    @GetMapping("/teachers")
    public ResponseEntity<?> getTeacher(
        @RequestParam(required = false) String email, 
        @RequestParam(required = false) String password) {
        if (email != null && password != null) {
            Teacher teacher = teacherService.getTeacherByEmail(email);
            if (teacher != null && passwordEncoder.matches(password, teacher.getPassword())) {
                return ResponseEntity.ok(teacher);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.ok(teacherService.getAllTeachers());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudent(
        @RequestParam(required = false) Long id, 
        @RequestParam(required = false) String password) {
        if (id != null && password != null) {
            Student student = studentService.getStudentById(id);
            if (student != null && passwordEncoder.matches(password, student.getPassword())) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.ok(studentService.getAllStudents());
        }
    }

    @PostMapping("/register-teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherRegistrationDto dto) {
        try {
            Teacher teacher = new Teacher(dto.getName(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
            teacherService.registerTeacher(teacher);
            return ResponseEntity.ok(Map.of("message", "Registration successful"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Email already in use"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Registration failed"));
        }
    }

    @PostMapping("/register-student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDto dto) {
        try {
            Student student = new Student(dto.getName(), passwordEncoder.encode(dto.getPassword()), dto.getLevel(), dto.getLanguage());
            studentService.registerStudent(student);
            return ResponseEntity.ok(Map.of("message", "Registration successful"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "ID already in use"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Registration failed"));
        }
    }

    @RequestMapping("/login-success")
    public ResponseEntity<String> loginSuccess() {
        return ResponseEntity.ok("Login successful");
    }

    @RequestMapping("/login-failure") 
    public ResponseEntity<String> loginFailure() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Login failed");
    }
}