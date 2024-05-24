package org.moment.moment_be.domain.board.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private Long post_id;

    private Long student_id;

    private Long parent_comment_id;

    private String content;

    @UpdateTimestamp
    private Timestamp created_at;

}
