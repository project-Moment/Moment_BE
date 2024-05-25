package org.moment.moment_be.domain.board.dto;

import lombok.Data;
import org.moment.moment_be.domain.board.entity.Post;

import java.util.List;

@Data
public class PostWithCommentDto {
    private Post post;
    private List<CommentDto> comments;
}
