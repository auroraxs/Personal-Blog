package com.moyu.controller;

import com.moyu.common.Result;
import com.moyu.pojo.Category;
import com.moyu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result selectAll() {
        return categoryService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(value = "currPage", defaultValue = "1", required = true) Integer currPage,
            @RequestParam(value = "pageSize", defaultValue = "2", required = true) Integer pageSize,
            @RequestParam(value = "categoryName", defaultValue = "", required = false) String categoryName) {
        Result pageResult = categoryService.selectPage(currPage, pageSize, categoryName);
        Result countResult = categoryService.selectCount(categoryName);
        if(pageResult.getState().equals("200") && countResult.getState().equals("200")) {
            return new Result().success()
                    .set("categoryList", pageResult.getData().get("categoryList"))
                    .set("count", countResult.getData().get("count"));
        }

        return new Result().error();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable String id){
        return categoryService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        return categoryService.deleteById(id);
    }

}
