package com.springboot.blog.controller;

import com.springboot.blog.contracts.PostDto;
import com.springboot.blog.contracts.PostResponse;
import com.springboot.blog.contracts.SearchRequest;
import com.springboot.blog.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private IPostService iPostService;

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto post = iPostService.createPost(postDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<PostResponse> getPosts(SearchRequest searchRequest) {
        PostResponse posts = iPostService.getAllPosts(searchRequest.getPageNo(), searchRequest.getPageSize(), searchRequest.getSortBy()
                , searchRequest.getSortDir());

        System.out.println(searchRequest.toString());
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

//    @GetMapping
//    public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "pageNo",required = false,defaultValue = "0") int pageNo,
//                                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
//                                                 @RequestParam(value = "sortBy",required = false,defaultValue = "id") String sortBy,
//                                                 @RequestParam(value = "sortDir",required = false,defaultValue = "asc") String sortDir)
//    {
//
//       PostResponse posts = iPostService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
//
//        return ResponseEntity.status(HttpStatus.OK).body(posts);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        PostDto posts = iPostService.getAllPostById(id);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto posts = iPostService.updatePost(postDto, id);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePostById(@PathVariable(name = "id") long id) {
        iPostService.deletePost(id);

        return ResponseEntity.status(HttpStatus.OK).body("data deleted successfully");
    }
}
