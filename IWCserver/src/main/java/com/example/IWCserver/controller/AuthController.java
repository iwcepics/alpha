package com.example.IWCserver.controller;

import com.example.IWCserver.dto.LoginRequest;
import com.example.IWCserver.dto.StudentRegistrationDto;
import com.example.IWCserver.dto.TeacherRegistrationDto;
import com.example.IWCserver.entity.Role;
import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.service.TeacherService;
import com.example.IWCserver.service.StudentService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints related to authentication
 */
//@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

@RestController
@RequestMapping("/api/auth")
@EnableMethodSecurity
public class AuthController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            logger.debug("Login attempt for user: {}", loginRequest.getIdentifier());
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getIdentifier(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login successful");

        } catch (AuthenticationException e) {
            logger.error("Login failed for user: {} - Reason: {}", 
                loginRequest.getIdentifier(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherRegistrationDto dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        teacher.setRole(Role.TEACHER);
        
        teacherService.registerTeacher(teacher);
        return ResponseEntity.ok("Teacher registered successfully");
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDto dto) {
        try {
            StudentRegistrationDto registeredStudent = studentService.registerStudent(dto);
            return ResponseEntity.ok(registeredStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Registration failed");
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