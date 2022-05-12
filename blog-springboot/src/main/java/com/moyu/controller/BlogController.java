package com.moyu.controller;

import com.moyu.common.Result;
import com.moyu.pojo.Blog;
import com.moyu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public Result selectAll() {
        return blogService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(value = "currentPage", defaultValue = "1", required = true) Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,
            @RequestParam(value = "title", defaultValue = "", required = false) String title) {
        Result pageResult = blogService.selectPage(currentPage, pageSize, title);
        Result countResult = blogService.selectCount(title);
        if(pageResult.getState().equals("200") && countResult.getState().equals("200")) {
            return new Result().success()
                    .set("blogList", pageResult.getData().get("blogList"))
                    .set("count", countResult.getData().get("count"));
        }
        return new Result().error();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") String id) {
        return blogService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return blogService.deleteById(id);
    }


}
