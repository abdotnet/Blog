package com.springboot.blog.controller;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.PagedResponse;
import com.springboot.blog.contracts.SearchRequest;
import com.springboot.blog.service.ICommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    private ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    @RequestMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable(name = "postId") long postId) {

        CommentDto newCommentDto = commentService.createComment(postId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCommentDto);
    }

    @GetMapping
    @RequestMapping("/post/{postId}/comment")
    public ResponseEntity<PagedResponse> getAllComments(SearchRequest searchRequest, @PathVariable(name = "postId") long postId) {

        PagedResponse pagedResponse = commentService.getAllCommentsByPostId(searchRequest, postId);

        return ResponseEntity.status(HttpStatus.OK).body(pagedResponse);
    }

    @GetMapping
    @RequestMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getSingleCommentByPostId(@PathVariable(name = "commentId") long commentId, @PathVariable(name = "postId") long postId) {

        CommentDto commentDto = commentService.getCommentsByPostId(commentId, postId);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }
}
