package org.moment.moment_be.domain.board.controller;

import org.moment.moment_be.domain.board.entity.Comment;
import org.moment.moment_be.domain.board.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RestController
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        comment.setParentCommentId(null);
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PostMapping("/{postId}/recomment/{commentId}")
    public ResponseEntity<Comment> recomment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        comment.setParentCommentId(commentId);
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
