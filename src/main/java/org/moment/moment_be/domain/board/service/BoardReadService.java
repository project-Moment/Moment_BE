package org.moment.moment_be.domain.board.service;


import org.moment.moment_be.domain.board.dto.PostDto;
import org.moment.moment_be.domain.board.entity.Post;
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

    @Autowired
    public BoardReadService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
        return posts.stream().map(this::converToDto).collect(Collectors.toList());
    }

    public Post getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
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
