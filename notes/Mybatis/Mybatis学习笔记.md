## Mybatis学习笔记

## 0. 写在前面

哔哩哔哩教程：【狂神说Java】Mybatis最新完整教程IDEA版通俗易懂，https://www.bilibili.com/video/BV1NE411Q7Nx?from=search&seid=10983546160906790779&spm_id_from=333.337.0.0

官方文档：https://mybatis.org/mybatis-3/zh/index.html

GitHub：https://github.com/mybatis/mybatis-3

Maven：https://mvnrepository.com/search?q=mybatis

## 1. 什么是Mybatis

MyBatis 是一款优秀的**持久层框架**，它支持自定义 SQL、存储过程以及高级映射。MyBatis **免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作**。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

## 2. 持久化和持久层

### 2.1 数据持久化

- 数据持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：断电即失
- 数据库（JDBC）、IO持久化

### 2.2 持久层

Dao层、Service层、Controller层

- Dao层完成数据库相关的操作
- Service层调用Dao层处理业务逻辑
- Controller层接收用户的请求，调用Service层处理用户请求

## 3. 入门案例

### 3.1 安装

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

### 3.2 Mybatis配置及工具类

每个基于 MyBatis 的应用都是以一个 **SqlSessionFactory** 的实例为核心的。**SqlSessionFactory** 的实例可以通过 **SqlSessionFactoryBuilder** 获得。而 **SqlSessionFactoryBuilder** 则可以从 **XML** 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。

配置及使用Mybatis按照如下的顺序进行操作

#### 3.2.1 配置XML文件

首先进行Mybatis系统的核心设置。在resources目录下新建config/mybatis-config.xml，并配置数据库相关信息（驱动，url，用户名、密码），这是为后续创建SqlSessionFactory作准备的

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--MyBatis 系统的核心设置-->
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis_study?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="sunxusen"/>
            </dataSource>
        </environment>
    </environments>
  
    <!--    每一个xml文件都需要在Mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

#### 3.2.2 Mybatis工具类配置

第二步，创建utils/MybatisUtils工具类，该工具类的主要作用是从XML文件中构建SqlSessionFactory，进而通过SqlSessionFactory获取SqlSession实例，下面的代码就是完成这两项工作的

```java
// Mybatis工具类
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    // 1. 从 XML 中构建 SqlSessionFactory
    static {
        try {
            String resource = "config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. 从 SqlSessionFactory 中获取 SqlSession
    public static SqlSession getSqlSession() {
        // true 设置自动提交事务
        return sqlSessionFactory.openSession(true);
    }
}
```

#### 3.2.3 测试

上面是Mybatis的总体配置，要使用Mybatis进行开始，一般有如下三个步骤

1. 创建实体类（注意：实体类中的字段要与数据库中的一致）
2. 创建实体类Dao接口
3. 创建接口实现类，在Mybatis中是穿件XML文件，XML文件就是接口实现类

**代码**

创建实体类

```java
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
}
```

创建实体类Dao接口

```java
public interface UserDao {

    List<User> getUserList();
}
```

创建接口实现UserMapper.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.moyu.dao.UserDao">

  <!--    id对应方法名-->
  <select id="getUserList" resultType="com.moyu.pojo.User">
    select * from User
  </select>
</mapper>
```

**注意：上述UserMapper.xml文件一定要在Mybatis核心配置文件中注册**

```xml
<!--    每一个xml文件都需要在Mybatis核心配置文件中注册-->
<mappers>
  <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```

接下来就可以在编写test测试上述代码是否可以正确执行了。

## 4. Mybatis核心配置文件

### 4.1 整体文件格式

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--MyBatis 系统的核心设置-->
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis_study?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="sunxusen"/>
            </dataSource>
        </environment>
    </environments>
  
    <!--    每一个xml文件都需要在Mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

### 4.2 环境配置-environments

在Mybatis中可以配置多套环境，如：开发、测试、生产环境等。在Mybatis配置文件中，environments标签定义了环境配置

**不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

```xml
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC">
      <property name="..." value="..."/>
    </transactionManager>
    <dataSource type="POOLED">
      <property name="driver" value="${driver}"/>
      <property name="url" value="${url}"/>
      <property name="username" value="${username}"/>
      <property name="password" value="${password}"/>
    </dataSource>
  </environment>
</environments>
```

- default：表示默认使用哪一套环境
- id：当前环境的唯一标识

在environments中定义了transactionManager（事务管理器）和dataSource（数据源）标签，其中

- 事务管理器的默认配置为JDBC，一般不用改
- 数据源的默认配置为POOLED，表示数据库连接池的意思

### 4.3 属性配置-properties

可以在外部配置properties文件，然后在核心配置文件中引入，并使用其中的配置

**properties文件**

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis_study?useSSL=false
username=root
password=sunxusen
```

**核心配置文件xml**

```xml
<!--    属性配置-->
<properties resource="config.properties"/>
```

### 4.4 类型别名-typeAliases

可以在Mybatis配置文件中配置类型别名，这样在mapper.xml文件中的resultType就可以不用写**全类名**，直接使用**别名**即可。

```xml
<typeAliases>
  <!--        第一种方式-->
  <typeAlias type="com.moyu.pojo.User" alias="User"/>
  <!--        第二种方式，扫描包-->
  <package name="com.moyu.pojo"/>
</typeAliases>
```

```xml
<mapper namespace="com.moyu.mapper.UserMapper">
  <select id="getUserList" resultType="User">
    select * from user
  </select>
</mapper>
```

在使用第二种方式的时候，会以被扫描包下的类名作为别名（不区分大小写，推荐小写）。也可以在实体类上使用注解修改别名

```java
@Alias("author")
public class Author {
    ...
}
```

### 4.5 设置- settings

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。具体有哪些设置参考官方文档。

### 4.6 映射器-mappers

mappers用来映射SQL语句，这里推荐使用以下方式，采用resource关键字引用到xml文件

```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```

## 5. MybatisConfig 工具类配置

```java
// Mybatis工具类
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    // 1. 从 XML 中构建 SqlSessionFactory
    static {
        try {
            String resource = "config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. 从 SqlSessionFactory 中获取 SqlSession
    public static SqlSession getSqlSession() {
        // true 设置自动提交事务
        return sqlSessionFactory.openSession(true);
    }
}
```

## 6. mapper.xml 映射文件

### 6.1 头文件格式

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.moyu.dao.UserDao">

  <!--    id对应方法名-->
  <select id="getUserList" resultType="com.moyu.pojo.User">
    select * from User
  </select>
</mapper>
```

### 6.1 namespace

命名空间，表示该mapper文件所对应的接口是哪个，使用全限定名，如：com.moyu.dao.UserDao

### 6.2 id

sql语句中的id属性对应接口中的方法名称

### 6.3 resultType

resultType用于指定sql语句的返回类型

### 6.4 parameterType

parameterType用于指定传递给sql语句的参数类型

### 6.5 resultMap

`resultMap` 元素是 MyBatis 中最重要最强大的元素。ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。

当数据库中的列名和Java实体类中的属性值名称不相同时，可以使用`resultMap` 来对结果集做一个映射。

```xml
<resultMap id="userResultMap" type="User">
  <!--column对应的是数据库列名，property对应的是Java实体类的属性-->
  <id property="id" column="user_id" />
  <result property="username" column="user_name"/>
  <result property="password" column="hashed_password"/>
</resultMap>

<!--resultMap的值要和上面resultMap标签中的id的值相同-->
<select id="selectUsers" resultMap="userResultMap">
  select user_id, user_name, hashed_password
  from some_table
  where id = #{id}
</select>
```

## 7. 日志工厂

在编写mysql语句的时候，难免会出现错误，可以使用日志来更好的排出错误。

配置方式，在Mybatis核心配置文件中，使用**settings**标签配置

### 7.1 标准日志输出

```xml
<!--    settings标签，配置日志-->
<settings>
  <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

### 7.2 LOG4J

配置即可，用时再学

## 8. 数据库代码编写

### 8.1 简单增删改查-xml文件

使用Mybatis进行CRUD操作的有以下两个步骤

1. 编写Dao/Mapper接口
2. 在对应的mapper文件中编写sql语句

**Dao接口**

```java
public interface UserDao {

    // 查询全部用户
    List<User> getUserList();

    // 根据id查询用户
    User getUserById(int id);

    // 增加一个用户
    Integer addUser(User user);

    // 更新用户信息
    Integer updateUser(User user);

    // 删除用户
    Integer deleteUserById(int id);
}
```

**XML文件**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.moyu.dao.UserDao">

<!--  id对应方法名，resultType是sql语句的返回值类型      -->
    <select id="getUserList" resultType="com.moyu.pojo.User">
        select * from user
    </select>

<!--    parameterType为传递进sql语句的参数类型-->
    <select id="getUserById" resultType="com.moyu.pojo.User" parameterType="int">
        select * from user where id = #{id}
    </select>


<!--    插入-->
    <insert id="addUser" parameterType="com.moyu.pojo.User">
        insert into user (id, username, password) values (#{id}, #{username}, #{password})
    </insert>

<!--    常规更新-->
    <update id="updateUser" parameterType="com.moyu.pojo.User">
        update user set username=#{username}, password=#{password} where id=#{id}
    </update>

<!--    删除-->
    <delete id="deleteUserById" parameterType="com.moyu.pojo.User">
        delete from user where id = #{id}
    </delete>

</mapper>
```

### 8.2 简单增删改查-注解开发

注解用来实现简单的sql语句，用于简化开发。在使用注解时，可以不用配置mapper.xml文件，直接在接口interface中编写sql语句即可。

```java
public interface UserMapper {

    @Select("select * from user")
    List<User> getUserList();

    User getUserById(Integer id);

    @Insert("insert into user (id, username, password) values (#{id}, #{username}, #{password})")
    Integer addUser(User user);

    @Update("update user set username=#{username}, password=#{password} where id=#{id}")
    Integer updateById(User user);

  	// @Param()注解用于指定传入sql语句中的参数的名称指是什么
    @Delete("delete from user where id=#{id}")
    Integer deleteById(@Param("id") Integer id);
}
```

关于@Param()注解

- 用于指定传入sql语句中的参数的名称是什么
- 在基本类型和String类型时需要加上，引用类型不需要添加

**不用编写mapper.xml文件，但同样要在Mybatis核心配置文件中配置mapper标签**

```xml
<mappers>
  <!--        指向接口类（采用xml方式则指向对应的xml文件）-->
  <mapper class="com.moyu.mapper.UserMapper"/>
</mappers>
```

### 8.3 万能Map和模糊查询

当parameterType是实体类时，传递进来的参数是该类的所有属性，如果只想修改某一个属性，使用全类名的parameterType会导致一个问题，用户不想修改的属性也会被重写，一般会被置Null。

对于上述问题，可以使用动态sql解决，即使用if标签。还有一种野路子是使用map，如下所示

```java
Integer updateUserUseMap(Map<String, Object> map);
```

```xml
<update id="updateUserUseMap" parameterType="map">
  update user set password=#{mapPassword} where id = #{mapId}
</update>
```

指定parameterType的类型为map，这时候只会读取map中key的值，从而修改数据库中指定字段为map中key所对应的value。测试代码如下

```java
@Test
    public void testUpdateUserUseMap() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("mapId", 2);
        map.put("mapPassword", "mapPassword");
        int num = userDao.updateUserUseMap(map);
        System.out.println(num);

        sqlSession.commit();
        sqlSession.close();
    }
```

模糊查询有两种实现方式（建议使用第一种）

1. 在Java代码执行的时候，传递通配符 % %

   ```java
   List<User> userList = userDao.getUserLike("%张%");
   ```

2. 直接在sql中拼接得到，有可能会引发**sql注入**的问题

   ```xml
   select * from user where username like "%"#{value}"%"
   ```

### 8.4 复杂查询环境-ResultMap

在一对多、多对一的查询中使用ResultMap是一个非常合适的选择。拿老师和学生来举例：一个老师可以对应多个学生，一个学生之对应一个老师。在学生层面来看，是多对一的关系；在老师层面来看，是一对多的关系。在Mybatis中，对于这种复杂的查询，多对一可以使用关联association，一对多使用集合collection；而这两种都要借助ResuleMap来做结果映射来实现。

#### 8.4.1 关联-association

在Mynbatis中，有两种方式实现关联。

1. 嵌套 Select 查询：通过执行另外一个 SQL 映射语句来加载期望的复杂类型。（即子查询：详情见官网）
2. 嵌套结果映射：使用嵌套的结果映射来处理连接结果的重复子集。（即联表查询：详情见下面例子）

```xml
<select id="getStudents2" resultMap="StudentTeacher2">
  select s.id s_id, s.name s_name, t.id t_id, t.name t_name from student s, teacher t where s.tid=t.id
</select>

<resultMap id="StudentTeacher2" type="Student">
  <id column="s_id" property="id"/>
  <result column="s_name" property="name"/>
  <association property="teacher" javaType="Teacher">
    <id column="t_id" property="id"/>
    <result column="t_name" property="name"/>
  </association>
</resultMap>
```

在select标签中，通过resultMap属性将结果映射到id为StudentTeacher2的resultMap标签，在该标签中，使用关联association获取学生所对应的老师。

上述各标签的含义如下

- id：将一个列的值映射到一个简单数据类型（String, int, double, Date 等）的属性或字段。*id* 元素对应的属性会被标记为对象的标识符，在比较对象实例时使用。 这样可以提高整体的性能，尤其是进行缓存和嵌套结果映射（也就是连接映射）的时候。
- result：将一个列的值映射到一个简单数据类型（String, int, double, Date 等）的属性或字段。
- association：关联（association）元素处理“有一个”类型的关系。常见于多对一的关系。

#### 8.4.2 集合-collection

集合查询同样有两种方式：子查询（嵌套Select语句）、联表查询（按结果集）。下面以联表查询为例

```xml
<select id="getTeachers" resultMap="TeacherStudent">
  select
  t.id teacher_id,
  t.name teacher_name,
  s.id student_id,
  s.name student_name,
  s.tid student_tid
  from teacher t, student s where t.id=s.tid and t.id=#{id}
</select>

<resultMap id="TeacherStudent" type="Teacher">
  <id property="id" column="teacher_id"/>
  <result property="name" column="teacher_name"/>
  <collection property="students" ofType="Student">
    <id property="id" column="student_id"/>
    <result property="name" column="student_name"/>
    <result property="tid" column="student_tid"/>
  </collection>
</resultMap>
```

**注意：**在使用联表查询的时候一定要注意**别名**问题，即：要**起别名**且**column**中要使用的是别名，而非直接使用数据库中的列名。

### 8.5 动态SQL语句











## x. 待补充知识

1. Maven资源过滤，默认类加载路径是？
1. 数据库增删改查基础语句的编写、以及内外键的使用