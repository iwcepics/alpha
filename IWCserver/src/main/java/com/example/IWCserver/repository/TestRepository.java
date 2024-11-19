package com.example.IWCserver.repository;

import com.example.IWCserver.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Test findByName(String name);
}
