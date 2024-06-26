package org.moment.moment_be.domain.board.controller;


import org.moment.moment_be.domain.board.dto.PostDto;
import org.moment.moment_be.domain.board.dto.PostWithCommentDto;
import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.service.BoardReadService;
import org.moment.moment_be.domain.board.service.BoardWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardWriteService boardWriteService;
    private final BoardReadService boardReadService;

    @Autowired
    public BoardController(BoardWriteService boardWriteService, BoardReadService boardReadService) {
        this.boardWriteService = boardWriteService;
        this.boardReadService = boardReadService;
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {//, @AuthenticationPrincipal UserDetails userDetails) {
        //post.setStudentId(Integer.parseInt(userDetails.getUsername()));
        Post createdPost = boardWriteService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    /*@PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = boardWriteService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }*/

    @GetMapping("")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> posts = boardReadService.getPosts();
        if (posts.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostWithCommentDto> getPostById(@PathVariable Long postId) {
        PostWithCommentDto postWithCommentDto = boardReadService.getPostById(postId);
        return ResponseEntity.ok(postWithCommentDto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable String category) {
        List<PostDto> posts = boardReadService.getPostByCategory(category);
        if (!posts.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(posts);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        Post updatePost = boardWriteService.updatePost(postId, post);
        if (updatePost != null) return ResponseEntity.ok().body(updatePost);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        boardWriteService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
