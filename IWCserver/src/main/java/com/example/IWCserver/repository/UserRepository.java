package com.example.IWCserver.repository;

import com.example.IWCserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    T findByName(String name);
    T findByPassword(String password);
    T findByNameAndPassword(String name, String password);
}
