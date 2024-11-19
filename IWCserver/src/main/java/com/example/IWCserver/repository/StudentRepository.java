package com.example.IWCserver.repository;

import com.example.IWCserver.entity.Student;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends UserRepository<Student> {
    boolean existsByPassword(String password);
}