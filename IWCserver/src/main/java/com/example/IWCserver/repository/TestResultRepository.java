package com.example.IWCserver.repository;

import com.example.IWCserver.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    public TestResult findByStudentIdAndTestId(Long studentId, Long testId);
    public TestResult findByStudentNameAndTestName(String studentName, String testName);
}