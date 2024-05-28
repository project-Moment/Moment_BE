package org.moment.moment_be.domain.board.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentDto {
    private Long commentId;
    private Integer studentId;
    private String content;
    private Timestamp createdAt;
    private List<reCommentDto> recomment;
}
