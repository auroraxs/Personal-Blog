---
typora-root-url: ./imgs
---

# 前言
该项目是一个基于springboot和vue的前后端分离的个人博客系统，整个项目没有使用到非常复杂的技术，后端基本上就是一个增删改查的功能，前端是一个展示的功能。如果想学习springboot和vue框架，那么该项目是一个非常适合的入门级练手项目。下面的内容我将介绍一下网站的整体情况、以及搭建这样一个网站需要用到哪些具体的技术（附学习链接），并分享我在搭建该网站时的一个学习路线规划。

# 网站效果演示
前台网址：http://42.192.235.175/

后台网址：暂时不公开了，下面展示一些效果图。

**前台效果图**

<img src="/home.png" style="zoom:50%;" />

<img src="/detail.png" style="zoom:50%;" />

![](/category.png)

![](/category.png)

![](/about.png)

**后台效果图**

![](/back.png)

# 所需技术

整体上，该网站用到的技术是springboot和vue两个框架，并且没有涉及到非常深入的东西。对于后端来说，需要掌握以下知识

- Java基本语法
- mysql基本的增删改查
- maven基础
- mybatis框架（DAO层的一个框架）
- springboot框架的学习

对于前端来说，需要学习的内容有下面这些

- vue基本语法
- vue router（用来进行单页面开发的路由跳转的）
- axios（用于发送http请求，非常简洁、高效，容易上手）
- Element ui（饿了么团队开发的一个UI框架，非常适配vue）

以上就是搭建这个个人博客所需要的一些前置知识，看似很多，其实需要掌握的并没有很多。

# 学习路线

本节我将我在搭建该博客时的学习路线展示出来，从我个人角度而言，下面的这条学习路线将会帮助你在最快的时间搭建好一个属于你自己的网站，同时又能够让你对整个业务有一定的了解，不仅知道怎么写，还知道为什么要这么写。好了，废话不多少，直接安排

**后端**

- springboot：[黑马程序员SpringBoot2全套视频教程，springboot零基础到项目实战（spring boot2完整版）](https://www.bilibili.com/video/BV15b4y1a7yG?from=search&seid=4138562537199934331&spm_id_from=333.337.0.0)
- Maven：[黑马程序员Maven全套教程，maven项目管理从基础到高级，Java项目开发必会管理工具maven](https://www.bilibili.com/video/BV1Ah411S7ZE?p=11&spm_id_from=pageDriver)
- Mybatis：[【狂神说Java】Mybatis最新完整教程IDEA版通俗易懂](https://www.bilibili.com/video/BV1NE411Q7Nx?from=search&seid=10983546160906790779&spm_id_from=333.337.0.0)

**前端**

- Vue全家桶：[尚硅谷Vue2.0+Vue3.0全套教程丨vuejs从入门到精通](https://www.bilibili.com/video/BV1Zy4y1K7SH?p=1)
- axios：[易用、简洁且高效的http库](http://www.axios-js.com/)
- Element ui：[官方文档](https://element.eleme.cn/#/zh-CN/component/installation)

上述视频教程都是我亲自听过并筛选出来的，老师讲的都非常通俗易懂，相信大家看完也能够有所收获！

# 总结

学习是一个漫长但有趣的过程，在写代码这件事情上尤为明显。通过一步步的学习，从无到有的创建出一个属于自己的东西，那一定非常精彩吧！希望本文对你能有一丝丝帮助，也祝大家早日搭建自己的网站！

