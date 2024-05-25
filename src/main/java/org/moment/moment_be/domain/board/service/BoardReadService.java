package org.moment.moment_be.domain.board.service;


import org.moment.moment_be.domain.board.dto.CommentDto;
import org.moment.moment_be.domain.board.dto.PostDto;
import org.moment.moment_be.domain.board.dto.PostWithCommentDto;
import org.moment.moment_be.domain.board.entity.Comment;
import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.CommentRepository;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardReadService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public BoardReadService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
        return posts.stream().map(this::converToDto).collect(Collectors.toList());
    }

    public PostWithCommentDto getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        Post post = postOptional.get();
        List<Comment> comments = commentRepository.findCommentByPostId(postId);

        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();
        postWithCommentDto.setPost(post);
        postWithCommentDto.setComments(comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setCommentId(comment.getCommentId());
            commentDto.setContent(comment.getContent());
            commentDto.setStudentId(comment.getStudentId());
            commentDto.setCreatedAt(comment.getCreatedAt());
            return commentDto;
        }).collect(Collectors.toList()));
        return postWithCommentDto;
    }

    public List<PostDto> getPostByCategory(String category) {
        List<Post> posts = postRepository.findByCategory(category, Sort.by("postId").descending());
        return posts.stream().map(this::converToDto).collect(Collectors.toList());
    }

    private PostDto converToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setStudentId(post.getStudentId());
        postDto.setCategory(post.getCategory());
        postDto.setTitle(post.getTitle());
        postDto.setCreatedAt(post.getCreatedAt());
        return postDto;
    }

}
