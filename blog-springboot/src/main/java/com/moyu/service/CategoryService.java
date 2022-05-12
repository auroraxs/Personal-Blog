package com.moyu.service;

import com.moyu.common.IDUtils;
import com.moyu.common.Result;
import com.moyu.mapper.CategoryMapper;
import com.moyu.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Result selectAll() {
        List<Category> categoryList = categoryMapper.selectAll();
        return new Result().success().set("categoryList", categoryList);
    }

    public Result selectPage(Integer currPage, Integer pageSize, String categoryName) {
        int startIndex = (currPage - 1) * pageSize;
        List<Category> categoryList = categoryMapper.selectPage(startIndex, pageSize, categoryName);
        if(categoryList != null) {
            return new Result().success().set("categoryList", categoryList);
        }
        return new Result().error();
    }

    public Result selectCount(String name) {
        Integer count = categoryMapper.selectCount(name);
        if(count != null) {
            return new Result().success().set("count", count);
        }
        return new Result().error();
    }

    public Result selectById(String id) {
        Category category = categoryMapper.selectById(id);
        if (category != null) {
            return new Result().success().set("category", category);
        } else {
            return new Result().error();
        }
    }

    public Result save(Category category) {
        int affectRow;
        String id = IDUtils.getUUID();
        if (category.getId() == null) {
            category.setId(id);
            affectRow = categoryMapper.insertOne(category);
        } else {
            affectRow = categoryMapper.updateOne(category);
            id = category.getId();
        }
         if (affectRow > 0) {
            return new Result().success().set("id", id);
        } else {
            return new Result().error();
        }
    }

    public Result deleteById(String id) {
        int affectRow = categoryMapper.deleteById(id);
        if (affectRow > 0) {
            return new Result().success();
        } else {
            return new Result().error();
        }
    }

}
