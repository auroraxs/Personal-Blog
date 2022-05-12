package com.moyu.mapper;

import com.moyu.pojo.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testBlogMapper {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void test_selectAll() {
        List<Blog> blogs = blogMapper.selectAll();
        for(Blog blog : blogs) {
            System.out.println(blog);
        }
    }

}
