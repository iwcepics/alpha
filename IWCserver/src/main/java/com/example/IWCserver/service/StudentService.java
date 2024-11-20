package com.example.IWCserver.service;

//import com.example.IWCserver.dto.StudentRegistrationDto;
//import com.example.IWCserver.entity.Role;
import com.example.IWCserver.entity.Student;
//import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.repository.StudentRepository;

import java.util.List;
//import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    public Student registerStudent(Student student) {
        return (Student) studentRepository.save(student);
    }

    public Student getStudentByIdAndPassword(Long id, String password) {
        return studentRepository.findByIdAndPassword(id, password);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
}
