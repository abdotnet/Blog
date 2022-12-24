package com.springboot.blog.service;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.CommentResponse;
import com.springboot.blog.contracts.SearchRequest;

public interface ICommentService {
    CommentDto createComment(long postId , CommentDto commentDto);
    CommentResponse getAllResponse(SearchRequest searchRequest, long postId);
}