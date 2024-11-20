package com.example.IWCserver.service;

import com.example.IWCserver.entity.Student;
import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.repository.StudentRepository;
import com.example.IWCserver.repository.TeacherRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to find a teacher by email
        Teacher teacher = teacherRepository.findByEmail(username);
        if (teacher != null) {
            return new org.springframework.security.core.userdetails.User(
                teacher.getEmail(),
                teacher.getPassword(),
                new ArrayList<>()
            );
        }

        // Attempt to find a student by name
        Student student = studentRepository.findById(Long.parseLong(username)).orElse(null);
        if (student != null) {
            return new org.springframework.security.core.userdetails.User(
                student.getName(),
                student.getPassword(),
                new ArrayList<>()
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}