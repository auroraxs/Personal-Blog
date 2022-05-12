# Maven学习笔记

## 0. 写在前面

此文档对应《黑马程序员Maven全套教程，maven项目管理从基础到高级，Java项目开发必会管理工具maven》

链接：https://www.bilibili.com/video/BV1Ah411S7ZE?p=11&spm_id_from=pageDriver

Maven官网：https://maven.apache.org/

Maven中央仓库：https://mvnrepository.com/

## 1. Maven基础

Maven的本质就是一个项目管理工具，将项目开发和管理抽象成一个项目对象（POM）

POM（Project Object Model）：项目对象模型

### 1.1 Maven的作用

**项目构建**：提供标准的、跨平台的自动化项目构建方式

**依赖管理（Dependency）：**方便快捷的管理项目依赖的资源（jar包），避免资源间的版本冲突问题

统一开发结构：提供标准的、统一的项目结构

```
pom.xml --> 项目对象模型POM <-->依赖管理Dependency --> 本地 --> 私服 --> 中央仓库
```

### 1.2 Maven坐标

Maven坐标的主要构成

- groupId：定义当前Maven项目隶属的**组织名称**（通常是域名反写，例如：org.mybatis）
- artifactId：定义当前Maven**项目名称**（通常是模块名称，例如：CRM，SMS）
- Version：定义当前项目的版本号
- packaging：定义项目的打包方式

使用唯一标识，唯一性定位资源位置，通过改标识可以将资源的识别与下载工作交由机器完成。



### 1.3 Maven项目构建命令

Maven构建命令使用mvn开头，后面添加功能参数，可以一次执行多个命令，使用空格分割。

```
mvn compile	# 编译
mvn clear	# 清理
mav test	# 测试
mvn package	# 打包
mvn install	# 安装到本地仓库
```



看到了P9，未完待续……