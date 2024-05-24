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
    private Long post_id;

    private int student_id;

    private String category;

    private String title;

    private String content;

    @UpdateTimestamp
    private Timestamp created_at;


    /*public Post(int student_id, String category, String title, String content) {
        this.student_id = student_id;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public Post() {

    }*/
}
