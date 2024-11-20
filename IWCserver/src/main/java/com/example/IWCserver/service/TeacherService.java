package com.example.IWCserver.service;

import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher registerTeacher(Teacher teacher) {
        // Additional validation and business logic
        return (Teacher) teacherRepository.save(teacher);
    }

    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public Teacher getTeacherByEmailAndPassword(String email, String password) {
        return teacherRepository.findByEmailAndPassword(email, password);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
