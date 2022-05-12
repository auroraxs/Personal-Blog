package com.moyu.mapper;

import com.moyu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 使用@SpringBootTest注解进行测试
@SpringBootTest
public class testUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        List<User> users = userMapper.selectAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

}
