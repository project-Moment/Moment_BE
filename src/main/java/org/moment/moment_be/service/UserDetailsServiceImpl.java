package org.moment.moment_be.service;

import org.moment.moment_be.domain.student.entity.Student;
import org.moment.moment_be.domain.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Autowired
    public UserDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Student student = studentRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return new org.springframework.security.core.userdetails.User(student.getId().toString(), student.getPassword(), new ArrayList<>());
        /*Integer studentId = Integer.parseInt(id);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return new org.springframework.security.core.userdetails.User(student.getId().toString(), student.getPassword(),
                new ArrayList<>());*/
    }
}
