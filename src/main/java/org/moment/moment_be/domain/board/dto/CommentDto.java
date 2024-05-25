package org.moment.moment_be.domain.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDto {
    private Long commentId;
    private Long parentCommentId;
    private Long studentId;
    private String content;
    private Timestamp createdAt;
}
