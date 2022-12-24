package com.springboot.blog.service.impl;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.PagedResponse;
import com.springboot.blog.contracts.SearchRequest;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.BlogApiException;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.ICommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = modelMapper.map(commentDto, Comment.class);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", "" + postId));

        comment.setPost(post);
        comment = commentRepository.save(comment);
        commentDto.setId(comment.getId());

        return commentDto;
    }

    @Override
    public PagedResponse<CommentDto> getAllCommentsByPostId(SearchRequest searchRequest, long postId) {

        Pageable page = PageRequest.of(searchRequest.getPageNo(), searchRequest.getPageSize());

        Page<Comment> pageComment = commentRepository.findAll(page);

        List<CommentDto> data = pageComment.getContent().stream().filter(c -> c.getPost().getId() == postId)
                .map(c -> CommentDto.builder().id(c.getId()).body(c.getBody()).email(c.getEmail()).name(c.getName()
                ).build()).collect(Collectors.toList());

        PagedResponse pagedResponse = PagedResponse.builder()
                .pageNo(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalPages(pageComment.getTotalPages())
                .last(pageComment.isLast())
                .data(Collections.singletonList(data))
                .totalElement(pageComment.getTotalElements())
                .build();


        return pagedResponse;
    }

    @Override
    public CommentDto getCommentsByPostId(long commentId, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", "" + postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", "" + commentId));

        if (!comment.getPost().getId().equals(postId))
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment do not belong to the post");

        return CommentDto.builder().id(comment.getId()).build();
    }
}
