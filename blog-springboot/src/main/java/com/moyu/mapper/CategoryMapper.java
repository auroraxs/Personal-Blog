package com.moyu.mapper;

import com.moyu.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectAll();

    List<Category> selectPage(@Param("currPage") Integer currPage,
                              @Param("pageSize") Integer pageSize,
                              @Param("categoryName") String categoryName);

    Integer selectCount(@Param("categoryName") String categoryName);

    Category selectById(@Param("id") String id);

    Integer insertOne(Category category);

    Integer updateOne(Category category);

    Integer deleteById(@Param("id") String id);

}
