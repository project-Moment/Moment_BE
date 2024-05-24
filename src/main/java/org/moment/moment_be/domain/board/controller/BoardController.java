package org.moment.moment_be.domain.board.controller;


import org.moment.moment_be.domain.board.entity.Post;
import org.moment.moment_be.domain.board.service.BoardReadService;
import org.moment.moment_be.domain.board.service.BoardWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
