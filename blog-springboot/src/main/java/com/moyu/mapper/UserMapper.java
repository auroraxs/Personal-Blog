package com.moyu.mapper;

import com.moyu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// @Mapper注解的作用相当于在mybatis-config.xml中注册mapper.xml文件
@Mapper
public interface UserMapper {

    List<User> selectAll();

    List<User> selectPage(@Param("pageNum") Integer pageNum,
                          @Param("pageSize") Integer pageSize,
                          @Param("username") String username,
                          @Param("role") String role);

    Integer selectCount(@Param("username") String username,
                        @Param("role") String role);

    // @Param注解用来标识传递给sql语句的参数名称是什么
    User selectById(@Param("id") String id);

    Integer insertOne(User user);

    Integer updateOne(User user);

    Integer deleteById(@Param("id") String id);
}
