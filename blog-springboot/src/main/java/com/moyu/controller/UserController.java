package com.moyu.controller;

import com.moyu.common.Result;
import com.moyu.mapper.UserMapper;
import com.moyu.pojo.User;
import com.moyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @RestController表示：将该类标识为一个处理请求的处理器
@RestController
// @RequestMapping表示：标识http请求与该控制器中方法之间的映射关系
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(required = true, defaultValue = "1", value = "pageNum") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10", value = "pageSize") Integer pageSize,
            @RequestParam(required = false, defaultValue = "", value = "username") String username,
            @RequestParam(required = false, defaultValue = "", value = "role") String role) {
        int startIndex = (pageNum - 1) * pageSize;
        int total = userService.selectCount(username, role);
        List<User> userList = userService.selectPage(startIndex, pageSize, username, role);
        Result result = new Result().success();
        result.set("total", total).set("userList", userList);
        return result;
    }

    // @PathVariable注解：用于接收请求路径中占位符的值
    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") String id) {
        return userService.selectById(id);
    }

    // @RequestBody注解：将前台传过来的json对象转换为后台的Java对象
    @PostMapping
    public Result save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }
}
