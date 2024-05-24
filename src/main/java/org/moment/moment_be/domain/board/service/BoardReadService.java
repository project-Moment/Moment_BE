package org.moment.moment_be.domain.board.service;


import org.moment.moment_be.domain.board.dto.PostDto;
import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardReadService {

    private PostRepository postRepository;

    @Autowired
    public BoardReadService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPostById(Long post_id) {
        Optional<Post> postOptional = postRepository.findById(post_id);
        return postOptional.orElse(null);
    }

    public List<PostDto> getPostByCategory(String category) {
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPost_id(post.getPost_id());
            postDto.setStudent_id(post.getStudent_id());
            postDto.setCategory(post.getCategory());
            postDto.setTitle(post.getTitle());
            postDto.setCreated_at(post.getCreated_at());
            return postDto;
        }).collect(Collectors.toList());
    }

}
