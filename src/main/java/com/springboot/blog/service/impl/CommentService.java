package com.springboot.blog.service.impl;

import com.springboot.blog.contracts.CommentDto;
import com.springboot.blog.contracts.CommentResponse;
import com.springboot.blog.contracts.SearchRequest;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = Comment.builder().email(commentDto.getEmail())
                .body(commentDto.getBody())
                .name(commentDto.getName())
                .build();

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",""+postId));

        comment.setPost(post);
        comment = commentRepository.save(comment);
        commentDto.setId(comment.getId());

        return commentDto;
    }

    @Override
    public CommentResponse getAllResponse(SearchRequest searchRequest , long postId) {

        PageRequest page = PageRequest.of(searchRequest.getPageNo(), searchRequest.getPageSize());

    CommentDto data =    commentRepository.findAll().stream().map(comment -> CommentDto.builder().body(comment.getBody()) )

        return null;
    }
}