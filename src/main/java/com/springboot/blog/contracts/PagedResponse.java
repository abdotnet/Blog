package com.springboot.blog.contracts;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagedResponse<T> {
    private List<T> data;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private long totalPages;
    private boolean last;
}
