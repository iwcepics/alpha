package com.example.IWCserver.service;

import com.example.IWCserver.dto.StudentRegistrationDto;
import com.example.IWCserver.entity.Student;
import com.example.IWCserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentRegistrationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentRegistrationDto registerStudent(StudentRegistrationDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setLevel(dto.getLevel());
        student.setLanguage(dto.getLanguage());
        student.setRole(dto.getRole());

        String rawPassword = generateStudentPassword();
        student.setPassword(passwordEncoder.encode(rawPassword));

        Student savedStudent = studentRepository.save(student);

        // Return the raw password and ID to the teacher to provide to the student
        dto.setId(savedStudent.getId());
        dto.setPassword(rawPassword);

        return dto;
    }

    private String generateStudentPassword() {
        String password;
        Random random = new Random();
        do {
            password = String.format("%06d", random.nextInt(999999));
        } while (studentRepository.existsByPassword(passwordEncoder.encode(password)));
        return password;
    }
}
