package com.example.IWCserver.service;

import com.example.IWCserver.dto.StudentRegistrationDto;
import com.example.IWCserver.entity.Role;
import com.example.IWCserver.entity.Student;
import com.example.IWCserver.repository.StudentRepository;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentRegistrationDto registerStudent(StudentRegistrationDto dto) {
        // Validate input

        Student student = new Student();
        student.setName(dto.getName());
        student.setLevel(dto.getLevel());
        student.setLanguage(dto.getLanguage());
        student.setRole(Role.STUDENT);

        // Generate and set password
        String rawPassword = generateStudentPassword();
        student.setPassword(passwordEncoder.encode(rawPassword));

        // Save student
        Student savedStudent = studentRepository.save(student);

        // Update DTO with generated info
        dto.setId(savedStudent.getId());
        dto.setPassword(rawPassword);

        return dto;
    }

    // Keep the existing password generation and getAllStudents methods

    private String generateStudentPassword() {
        String password;
        Random random = new Random();
        do {
            password = String.format("%06d", random.nextInt(999999));
        } while (studentRepository.existsByPassword(passwordEncoder.encode(password)));
        return password;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
