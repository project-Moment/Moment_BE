package org.moment.moment_be.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_Id")
    private Long postId;

    @Column(name = "student_id")
    private Integer studentId;

    private String category;

    private String title;

    private String content;

    @UpdateTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
