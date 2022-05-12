package com.moyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    // 基本属性
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer views;
    private String published;
    private Date createTime;
    private String titleImage;

    // 实体类属性
    private Category category;
    private User user;

}
