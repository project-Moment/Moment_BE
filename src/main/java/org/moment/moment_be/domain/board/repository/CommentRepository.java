package org.moment.moment_be.domain.board.repository;

import org.moment.moment_be.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndParentCommentIdIsNull(Long postId);
    List<Comment> findByParentCommentId(Long parentCommentId);
}
