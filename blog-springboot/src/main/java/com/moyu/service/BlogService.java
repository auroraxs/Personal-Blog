package com.moyu.service;

import com.moyu.common.IDUtils;
import com.moyu.common.Result;
import com.moyu.mapper.BlogMapper;
import com.moyu.pojo.Blog;
import com.moyu.pojo.Category;
import com.moyu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    // 查询全部博客
    public Result selectAll() {
        List<Blog> blogs = blogMapper.selectAll();
        if (blogs.size() == 0) {
            return new Result().error();
        } else {
            return new Result().success().set("blogs", blogs);
        }
    }

    public Result selectPage(Integer currentPage, Integer pageSize, String name) {
        int startIndex = (currentPage - 1) * pageSize;
        List<Blog> blogList = blogMapper.selectPage(startIndex, pageSize, name);
        if(blogList != null) {
            return new Result().success().set("blogList", blogList);
        }
        return new Result().error();
    }

    public Result selectCount(String title) {
        Integer count = blogMapper.selectCount(title);
        if(count != null) {
            return new Result().success().set("count", count);
        }
        return new Result().error();
    }

    // 根据Id查询博客
    public Result selectById(String id) {
        Blog blog = blogMapper.selectById(id);
        if(blog != null) {
            return new Result().success().set("blog", blog);
        } else {
            return new Result().error();
        }
    }

    // 保存博客（包括：分类、用户）
    public Result save(Blog blog) {
        blog.setCreateTime(new Date());
        Category category = blog.getCategory();
        User user = blog.getUser();

        if (category != null) {
            blog = checkBlogWithCategory(blog);
        }
        if (user != null) {
            blog = checkBlogWithUser(blog);
        }

         return saveBlog(blog);
    }

    public Result saveBlog(Blog blog){
        int affectRow;
        if (blog.getId() == null) {
            blog.setId(IDUtils.getUUID());
            affectRow = blogMapper.insertOne(blog);
        } else {
            affectRow = blogMapper.updateOne(blog);
        }
        if(affectRow > 0) {
            return new Result().success();
        } else {
            return new Result().error();
        }
    }

    // 检查博客中是否存在分类属性，进行新增
    public Blog checkBlogWithCategory(Blog blog) {
        Category category = blog.getCategory();
        // 新增分类并拿到新增之后的category_id
        if(category.getId() == null) {
            String category_id = (String) categoryService.save(category).getData().get("id");
            category.setId(category_id);
        }
        return blog;
    }

    // 检查博客中是否存在用户属性，进行新增
    public Blog checkBlogWithUser(Blog blog) {
        User user = blog.getUser();
        // 新增用户并拿到新增之后的user_id
        if(user.getId() == null) {
            String user_id = (String) userService.save(user).getData().get("id");
            user.setId(user_id);
        }
        return blog;
    }

    public Result deleteById(String id) {
        int affectRow = blogMapper.deleteById(id);
        if (affectRow > 0) {
            return new Result().success();
        } else {
            return new Result().error();
        }
    }

}
