package com.springboot.blog.blog;

import com.springboot.blog.contracts.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);

        Assert.notNull(commentDto, "not null");
    }

}
