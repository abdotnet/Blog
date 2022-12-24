package com.springboot.blog.service.impl;

import com.springboot.blog.contracts.PostDto;
import com.springboot.blog.contracts.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.IPostService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post newPost = Post.builder()
                .description(postDto.getDescription())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        newPost = postRepository.save(newPost);
        postDto.setId(newPost.getId());
        return postDto;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> page = postRepository.findAll(pageable);

        List<PostDto> data = page.getContent()
                .stream().map(c -> PostDto.builder().id(c.getId()).
                        title(c.getTitle()).description(c.getDescription()).content(c.getContent()).build())
                .collect(Collectors.toList());

        PostResponse postResponse = PostResponse.builder().data(data).pageNo(page.getNumber()).
                pageSize(page.getSize()).
                totalPages(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .last(page.isLast())
                .build();

        return postResponse;
    }

    @Override
    public PostDto getAllPostById(long id) {
        PostDto postDto = postRepository.findById(id).map(c -> PostDto.builder().id(c.getId()).
                        title(c.getTitle()).description(c.getDescription()).content(c.getContent()).build()).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Post", "id", "" + id));

        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Post", "id", "" + id));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        post = postRepository.save(post);

        postDto = PostDto.builder()
                .content(post.getContent())
                .description(post.getDescription())
                .title(post.getTitle())
                .build();


        return postDto;
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Post", "id", "" + id));

        postRepository.delete(post);
    }
}