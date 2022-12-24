package com.springboot.blog.controller;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.CommentResponse;
import com.springboot.blog.contracts.SearchRequest;
import com.springboot.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    @RequestMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable(name = "postId") long postId) {

        CommentDto newCommentDto = commentService.createComment(postId, commentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCommentDto);

    }

    @GetMapping
    @RequestMapping("/post/{postId}/comments")
    public ResponseEntity<CommentResponse> getComments(SearchRequest searchRequest , @PathVariable(name = "postId") long postId) {

        CommentResponse commentResponse = commentService.getAllResponse(searchRequest, postId);

        return ResponseEntity.status(HttpStatus.OK).body(commentResponse);

    }
}