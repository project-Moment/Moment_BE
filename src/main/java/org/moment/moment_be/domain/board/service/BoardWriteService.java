package org.moment.moment_be.domain.board.service;

import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardWriteService {

    private PostRepository postRepository;

    @Autowired
    public BoardWriteService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }
}
