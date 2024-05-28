package org.moment.moment_be.domain.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDto {
    private Long postId;
    private String title;
    private Integer studentId;
    private String category;
    private Timestamp createdAt;
}
