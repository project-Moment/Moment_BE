package org.moment.moment_be.domain.board.service;

import org.moment.moment_be.domain.board.entity.Comment;
import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.CommentRepository;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
}
