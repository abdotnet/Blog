package com.springboot.blog.service;

import com.springboot.blog.contracts.PostDto;
import com.springboot.blog.contracts.PostResponse;
import com.springboot.blog.entity.Post;

import java.util.List;

public interface IPostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getAllPostById(long id);
    PostDto updatePost(PostDto postDto ,long id);
    void deletePost(long id);
}