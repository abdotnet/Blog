package com.springboot.blog.service;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.PagedResponse;
import com.springboot.blog.contracts.SearchRequest;

public interface ICommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    PagedResponse<CommentDto> getAllCommentsByPostId(SearchRequest searchRequest, long postId);

    CommentDto getCommentsByPostId(long commentId, long postId);
}
