package org.moment.moment_be.domain.board.service;

import org.moment.moment_be.domain.board.dto.CommentDto;
import org.moment.moment_be.domain.board.dto.reCommentDto;
import org.moment.moment_be.domain.board.entity.Comment;
import org.moment.moment_be.domain.board.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    private reCommentDto convertToReCommentDto(Comment comment) {
        reCommentDto reCommentDto = new reCommentDto();
        reCommentDto.setCommentId(comment.getCommentId());
        reCommentDto.setStudentId(comment.getStudentId());
        reCommentDto.setContent(comment.getContent());
        reCommentDto.setCreatedAt(comment.getCreatedAt());
        return reCommentDto;
    }

}
