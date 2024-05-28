package org.moment.moment_be.domain.student.service;

import org.moment.moment_be.domain.student.entity.Student;
import org.moment.moment_be.domain.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Optional<Student> login(Integer id, String password) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent() && passwordEncoder.matches(password, student.get().getPassword())) {
            return student;
        }
        return Optional.empty();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }
}
