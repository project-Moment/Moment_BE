package org.moment.moment_be.domain.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDto {
    private Long post_id;
    private String title;
    private int student_id;
    private String category;
    private Timestamp created_at;
}
