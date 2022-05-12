package com.moyu.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String role;

    private List<Blog> blogs = new ArrayList<>();
}
