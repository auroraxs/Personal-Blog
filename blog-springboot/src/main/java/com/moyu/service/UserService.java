package com.moyu.service;

import com.moyu.common.IDUtils;
import com.moyu.common.Result;
import com.moyu.mapper.UserMapper;
import com.moyu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service注解表示将该类注入到spring容器中，从而使得其能够注入到其他类中
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public List<User> selectPage(Integer pageNum, Integer pageSize, String username, String role) {
        return userMapper.selectPage(pageNum, pageSize, username, role);
    }

    public Integer selectCount(String username, String role) {
        return userMapper.selectCount(username, role);
    }

    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    public Result save(User user) {
        int affectRow;
        String id = IDUtils.getUUID();
        if(user.getId() == null) {
            user.setId(id);
            affectRow = userMapper.insertOne(user);
        } else {
            affectRow = userMapper.updateOne(user);
            id = user.getId();
        }
        if (affectRow > 0) {
            return new Result().success().set("id", id);
        } else {
            return new Result().error();
        }
    }

    public Result deleteById(String id) {
        int affectRow = userMapper.deleteById(id);
        if (affectRow == 0) {
            return new Result().error();
        } else {
            return new Result().success();
        }
    }
}
