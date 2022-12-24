package com.springboot.blog.contracts;

import com.springboot.blog.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private long id;
    private String name;
    private  String email;
    private  String body;
}