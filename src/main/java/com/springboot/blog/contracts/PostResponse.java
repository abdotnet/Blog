package com.springboot.blog.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostResponse {
   private List<PostDto> data;
   private int pageNo;
   private  int pageSize;
   private long totalElement;
   private  long totalPages;
   private  boolean last;
}