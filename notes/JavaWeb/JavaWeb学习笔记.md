# JavaWeb学习笔记

# 0. 写在前面

此笔记来源于：尚硅谷最新版JavaWeb全套教程,java web零基础入门完整版

链接：https://www.bilibili.com/video/BV1Y7411K7zz?p=182

# 1. XML

## 1.1 基本语法

xml是可扩展的标记行语言。他的主要作用有以下几个：

1. 用来保存数据，而且这些数据具有自我描述性。
2. 用作项目或者模块的配置文件。

```xml
<students>
  <student>
  	<id>1</id>
    <name>张三</name>
  </student>
  <student>
  	<id>2</id>
    <name>李四</name>
  </student>
</students>
```

**注意：**xml文件必须有根标签。

## 1.2 xml解析技术

xml本质上和html基本相同，都可以使用**dom**技术类解析。

# 2. Tomcat

Tomcat是由Apache提供的一种Web服务器，提供对jsp和servlet的支持，轻量级Web服务器，应用最广。

# 3. Servlet

Servlet是Java EE规范之一，也是JavaWeb三大组件之一。三大组件分别是：Servlet程序、Filter过滤器、Listener监听器。

Servlet是运行在服务器上的一个Java程序，**他可以接受客户端发送过来的请求，并响应数据给客户端。**

## 3.1 实现servlet程序的三个步骤

1. 编写一个类去实现Servlet接口
2. 实现接口中的service方法，处理请求，并响应数据
3. 到web.xml中去配置servlet程序的访问地址

## 3.2 servlet的生命周期

1. 执行servlet构造器方法
2. 执行init初始化方法
3. 执行service方法
4. 执行destroy销毁方法

## 3.3 servletConfig

用于获取**servlet**初始化参数信息，不能获取**context**的信息

## 3.4 servletContext

用于获取**context**的信息，不能获取**servlet**的信息。在web工程部署启动时创建，在web工程停止时销毁。

- servletContext是一个接口，表示servlet上下文对象
- 一个web工程，只有一个servletContext对象实例
- servletContext是一个域对象。域对象是可以想Map一样存取数据的对象，域指的是存取数据的范围，整个web工程。

## 3.5 HTTP

**常见响应状态码说明：**

- 200：表示请求成功
- 302：表示请求重定向
- 404：表示请求服务器已经收到，但是你要的数据不存在（请求地址错误）
- 500：表示服务器已经收到，但是服务器内部错误（代码错误）

### 3.5.1 HttpServletRequest

该类的作用：每次只要有请求进入tomcat服务器，tomcat服务器就会把请求过来的HTTP歇息信息解析好封装到Request对象中，然后传递到service()方法中供我们使用。我们可以通过HttpServletRequest对象，获取到所有请求的信息。

**常用方法**

- getRequestURI()——获取请求的资源路径
- getRequestURL()——获取请求的统一资源定位符（绝对路径）
- getRemoteHost()——获取客户端IP地址
- getHeader()——获取请求头
- getParameter()——获取请求的参数
- getParameters()——获取请求的参数（多个值时使用）
- getMothod()——获取请求的方式
- setAttribute(key, value)——设置域数据
- getAttribute(key)——请求域数据
- getRequestDispatcher()——获取请求转发对象

**请求转发**（具体代码详见 JavaWeb/servlet_07）

servlet1 ==> servlet2

- 浏览器地址没有变化
- 他们是一次请求
- 共享Request域中的数据
- 可以转发到WEB- INF目录下，但是无法出站，访问外部目录。

### 3.5.2 HttpServletResponse

HttpServletResponse和HttpServletRequest类一样，每次请求进来，Tomcat服务器都会创建一个Response对象传递给servlet程序使用。



## 4. Java EE三层架构

![](/Users/sunxusen/Desktop/寒假技术学习/JavaWeb/assets/JavaEE三层架构.png)

分层是为了解藕，降低代码的耦合度，方便项目后期维护和升级。

| 层           | 包名                                | 说明              |
| ------------ | ----------------------------------- | ----------------- |
| web层        | com.atguigu.web/servlet/controller  |                   |
| service层    | com.atguigu.service                 | service接口包     |
|              | com.atguigu.service.impl            | service接口实现类 |
| dao层        | com.atguigu.dao                     | Dao接口包         |
|              | com.atguigu.dao.impl                | Dao接口实现类     |
| 实体bean对象 | com.atguigu.pojo/eneity/domain/bean | JavaBean类        |
| 测试包       | com.atguigu.test/junit              |                   |
| 工具类       | com.atguigu.utils                   |                   |

### 4.1 开发步骤

1. 创建数据库和对应的表
2. 编写数据库对应的JavaBean，即entity或者pojo目录下的类
3. 编写工具类 JdbcUtils类
4. 编写BaseDao
5. 编写Dao持久层



书城项目使用到的**jar包**

- Mysql-connector-java——Java通过jdbc访问数据库需要该包的支持
- Druid——数据库连接池的jar包
- junit——单元测试的jar包
- Hamcrest