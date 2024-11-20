package com.example.IWCserver.repository;

import com.example.IWCserver.entity.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByName(String name);
    Teacher findByPassword(String password);
    Teacher findByNameAndPassword(String name, String password);
    Teacher findByEmail(String email);
    Teacher findByEmailAndPassword(String email, String password);
}
