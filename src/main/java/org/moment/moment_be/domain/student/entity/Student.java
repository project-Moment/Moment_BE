package org.moment.moment_be.domain.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    private String password;

    @Column(name = "phone_number")
    private String phone;

    private String email;

    private String dept;

    @UpdateTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
