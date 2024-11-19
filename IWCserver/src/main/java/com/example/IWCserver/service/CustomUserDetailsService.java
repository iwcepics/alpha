package com.example.IWCserver.service;

import com.example.IWCserver.entity.User;
import com.example.IWCserver.entity.Teacher;
import com.example.IWCserver.repository.TeacherRepository;
import com.example.IWCserver.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = null;

        // Attempt to load as a teacher using email
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(identifier);
        if (teacherOpt.isPresent()) {
            user = teacherOpt.get();
        } else {
            // Attempt to parse identifier as ID for student
            try {
                Long studentId = Long.parseLong(identifier);
                user = studentRepository.findById(studentId)
                        .orElseThrow(() -> new UsernameNotFoundException("Student not found with id: " + studentId));
            } catch (NumberFormatException e) {
                throw new UsernameNotFoundException("User not found with identifier: " + identifier);
            }
        }
        
        return new org.springframework.security.core.userdetails.User(
                identifier,
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String role = "ROLE_" + user.getRole().name();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
