package com.moyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    // 基本属性
    private String id;
    private String name;

    // 实体类属性
    private List<Blog> blogs = new ArrayList<>();

}
