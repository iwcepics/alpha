package com.example.IWCserver;

import com.example.IWCserver.controller.AuthController;
import com.example.IWCserver.dto.LoginRequest;
import com.example.IWCserver.dto.StudentRegistrationDto;
import com.example.IWCserver.dto.TeacherRegistrationDto;
import com.example.IWCserver.entity.Student;
import com.example.IWCserver.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private StudentService studentService;

    @Test
    public void testTeacherLoginAndRegisterStudent() {
        // Register a teacher
        
        TeacherRegistrationDto teacherDto = new TeacherRegistrationDto();
        teacherDto.setName("Teacher 1");
        teacherDto.setEmail("teacher1@gmail.com");
        teacherDto.setPassword("password");
        ResponseEntity<?> teacherResponse = authController.registerTeacher(teacherDto);
        assertEquals(200, teacherResponse.getStatusCode().value());
        
        // Log in as the teacher
        
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setIdentifier("teacher1@gmail.com");
        loginRequest.setPassword("password");
        ResponseEntity<String> loginResponse = authController.login(loginRequest);
        assertEquals(200, loginResponse.getStatusCode().value());
        
        // Register students
        StudentRegistrationDto studentDto = new StudentRegistrationDto();
        studentDto.setName("Student 1");
        studentDto.setLevel('B');
        studentDto.setLanguage("English");
        ResponseEntity<?> studentResponse = authController.registerStudent(studentDto);
        assertEquals(200, studentResponse.getStatusCode().value());

        // Verify the student was registered
        Iterable<Student> students = studentService.getAllStudents();
        boolean studentFound = false;
        for (Student student : students) {
            if (student.getName().equals("Student 1")) {
                studentFound = true;
                break;
            }
        }
        assertTrue(studentFound);
    }
}