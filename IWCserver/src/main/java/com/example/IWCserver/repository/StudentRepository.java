package com.example.IWCserver.repository;

import com.example.IWCserver.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
    Student findByPassword(String password);
    Student findByNameAndPassword(String name, String password);
    Student existsByPassword(String password);
    Student findByIdAndPassword(Long id, String password);

}