package com.example.IWCserver.repository;

import com.example.IWCserver.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByEmailAndPassword(String email, String password);
}
