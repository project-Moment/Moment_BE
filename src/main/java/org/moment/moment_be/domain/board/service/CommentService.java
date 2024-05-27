package org.moment.moment_be.domain.board.service;

import org.moment.moment_be.domain.board.dto.CommentDto;
import org.moment.moment_be.domain.board.dto.reCommentDto;
import org.moment.moment_be.domain.board.entity.Comment;
import org.moment.moment_be.domain.board.repository.CommentRepository;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getCommentByPostId(Long postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostIdAndParentCommentIdIsNull(postId);
        return comments.stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setStudentId(comment.getStudentId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreatedAt(comment.getCreatedAt());

        List<Comment> reComments = commentRepository.findByParentCommentId(comment.getCommentId());
        List<reCommentDto> reCommentDto = reComments.stream()
                .map(this::convertToReCommentDto)
                .collect(Collectors.toList());

        commentDto.setRecomment(reCommentDto);
        return commentDto;
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
