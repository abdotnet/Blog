package com.springboot.blog.contracts;

import com.springboot.blog.utils.AppConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequest {
    private int pageNo = 0;
    private  int pageSize = 10;
    private  String sortBy ="id";
    private  String sortDir ="asc";
    private  String search;

}