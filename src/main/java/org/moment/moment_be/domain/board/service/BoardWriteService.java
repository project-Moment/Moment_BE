package org.moment.moment_be.domain.board.service;

import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardWriteService {

    private final PostRepository postRepository;

    @Autowired
    public BoardWriteService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post post) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()) {
            Post updatedPost = optionalPost.get();

            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            updatedPost.setCategory(post.getCategory());

            return postRepository.save(updatedPost);
        } else return null;
    }

    public void deletePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) postRepository.deleteById(postId);
    }
}
