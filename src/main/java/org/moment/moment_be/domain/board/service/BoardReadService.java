package org.moment.moment_be.domain.board.service;


import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
