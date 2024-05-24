package org.moment.moment_be.domain.board.controller;


import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.service.BoardReadService;
import org.moment.moment_be.domain.board.service.BoardWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController {

    private BoardWriteService boardWriteService;
    private BoardReadService boardReadService;

    @Autowired
    public BoardController(BoardWriteService boardWriteService, BoardReadService boardReadService) {
        this.boardWriteService = boardWriteService;
        this.boardReadService = boardReadService;
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = boardWriteService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    /*@GetMapping("")
    public ResponseEntity<Page<Post>> getPosts(Pageable pageable) {
        Page<Post> postsPage = boardReadService.getPosts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(postsPage);
    }*/

    @GetMapping("/{post_id}")
    public ResponseEntity<Post> getPost(@PathVariable Long post_id) {
        Post post = boardReadService.getPostById(post_id);
        if (post != null) return ResponseEntity.ok().body(post);
        else return ResponseEntity.notFound().build();
    }



}
