package org.moment.moment_be.domain.student.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.moment.moment_be.domain.student.entity.Student;
import org.moment.moment_be.domain.student.service.StudentService;
import org.moment.moment_be.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student registeredStudent = studentService.registerStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredStudent);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestParam Integer id, @RequestParam String password) {
        return studentService.login(id, password)
                .map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // 로그아웃
    /*@PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // 실제로 JWT 기반 인증에서는 서버 측에서 JWT를 무효화할 수 없습니다.
        // 그러나, 클라이언트 측에서 토큰을 삭제하도록 지시할 수 있습니다.
        return ResponseEntity.status(HttpStatus.OK).body("Successfully logged out");
    }*/
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }


    // 학생 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
