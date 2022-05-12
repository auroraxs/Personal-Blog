# Vue学习笔记

## 0.写在前面

此文档视频来源：尚硅谷Vue2.0+Vue3.0全套教程丨vuejs从入门到精通

链接：https://www.bilibili.com/video/BV1Zy4y1K7SH?p=1

## 1. Vue基础

**注意：**容器和vue实例之间的对应关系为一对一。

**注意：**vue管理的函数，一定不要写成箭头函数，一旦写了箭头函数，this就不再是vue实例了

### 1.1 基本指令

- v-bind：实现数据单向绑定，数据只能从data流向页面
- v-model：实现数据双向绑定，数据即可以从data流向页面，也可以从页面流向数据。只能应用在表单类元素，即输入类元素。
- v-on：事件绑定，简写符@
- v-show：根据true和false决定是否显示DOM元素
- @blur：绑定失去焦点事件

### 1.2 MVVM模型

MVVM模型分为以下部分

- M：模型（Model），对应data中的数据
- V：视图（View），对应模版
- **VM**：视图模型（View Model），对应Vue实例

data中的所有属性，最后都出现在了vm身上。vm身上的所有属性，以及vue原型上的所有属性，在Vue模版中都可以直接使用。

### 1.3 模版字符串

使用``。

## 2. 组件

组件是实现应用中局部功能代码和资源的集合。

非单文件组件：一个文件中包含有n个组件。

单文件组件：一个文件中只有1个组件。

### 2.1 基本使用

使用组件分为三个步骤：

1. 创建组件
2. 注册组件（分为局部注册和全局注册）
3. 使用组件

```js
// 创建组件
Vue.extend()

// 注册组件
// 1. 全局注册
Vue.component('', {})

// 局部注册
components: {
	school,
	student
}

// 使用组件
<school></school>
```

### 2.2 组件的命名

组件的命名有以下几种方式

1. 组件名字纯小写：直接命名即可
2. 组件名字包含多个单词：使用keyab-base命名，或者使用CamelCase命名（需要vue脚手架支持）。
   1. CamelCase命名也是vue官方推荐的方式。

### 2.3 单文件组件

单文件组件的命名方式

- school.vue
- School.vue（开发中推荐使用）
- my-school.vue
- MySchool.vue（开发中推荐使用）

在单文件组件的定义中需要指定暴露方式，有以下三种暴露方式（需要学习一下这三种暴露方式）

- 分别暴露
- 统一暴露
- 默认暴露（推荐使用）

```javascript
export default {

}
```

**总结**

在单文件组件形式中，main.js是入口文件，App.vue是统领其他所有组件的。在main.js文件中需要引入App.vue，在App.vue中需要引入其他的组件。

在执行的时候按照这样一个过程：首先读取main.js，在main.js中执行import语句，去到App.vue中执行其中的代码，再去寻找各个小组件，最终执行main.js中的new Vue({})代码块。

### 2.4 计算属性

在创建Vue实例的时候传入**computed**配置

### 2.5 监视属性

在vue中，当需要监视一个属性的变化时，可以使用watch监视属性。当被监视的属性发生变化时，回调函数自动调用，进行相关操作。监视属性的两种写法：

- 在创建Vue实例的时候传入watch配置
- 通过vm.$watch监视

Vue中的watch默认不监视对象内部值的变化（对象内部值变化，其实对象的地址没有变），如果需要监视对象内部值的变化，可以在watch中配置深度监视

```js
deep: true
```

## 3. vue cli 脚手架

### 3.1 脚手架的安装

**全局安装@vue/cli**

```
npm install -g @vue/cli
```

**创建项目**

```
vue create 项目名称
```

在创建项目的时候，会有一些选项，比如以下

- babel：ES6转ES5
- eslint：检查语法的（一般不要选）

**启动项目**

```
npm run serve
```

### 3.2 脚手架文件详解

`src/main.js`是整个vue工程的入口文件

`assets/`文件夹下通常放工程的静态资源

`src/`源代码文件夹

#### vue.config.js

**注意**：在使用vue/cli时，不要修改public、以及public文件夹下的文件名称（网站页签，index.html）、src、main.js文件名称。如果需要修改，需要创建vue.config.js文件，在其中修改。一旦修改这个文件，需要重新启动，运行 npm run serve。

**作用**：进行相关配置，如

- 代理配置

**位置**：在src目录外

### 3.3 render函数

创建vue实例的时候，其中的render函数本质上是如下函数

```javascript
render(createElement) {
	return createElement()
}
```

其中createElemen()是一个函数

### 3.4 ref属性

ref属性被用来给元素或者**子组件**注册**引用**信息，相当于id的替代者。既可以获取真实的DOM元素，也可以获取父组件中的子组件，用于组件通信。

```html
<template>
  <div>
    <h1 v-text="message" ref="title"></h1>
    <button @click="showDom" ref="btn">点击我输出上方DOM元素</button>
    <School ref="sch"></School>
  </div>
</template>
```

获取使用this.$ref。

### 3.5 组件通信

#### 父子通信-父传子

如果是父亲组件传递给子组件数据的话，使用props属性。在父组件使用的子组件标签中传递数据，在子组件中使用props属性接收。

#### 父子通信-子传父

当子组件需要传递数据给父组件的时候，可以采用如下方案

**方案一：通过props将父组件中的方法传递给子组件**

在父组件中定义一个方法，该方法接受一个参数。将该方法通过props属性传递给子组件，子组件在合适的时候调用该方法，将数据作为参数传递给父组件。

**方案二：通过自定义事件**

通过父组件给子组件绑定一个自定义事件，来实现子组件给父组件传递数据。在子组件中使用`this.$emit('事件名', 参数)`触发事件。事件的具体实现需要在父组件中定义。

```vue
<!-- 在App组件中给MyHeader组件绑定一个自定义事件 @addTodo中的addTodo是事件名，=后面的addTodo是回调函数名 -->
<MyHeader @addTodo="addTodo"></MyHeader>
```

**方案三：通过ref属性**

使用`ref`属性给子组件注册引用信息，这样在父组件中就可以访问子组件`vc`的信息，包括：data、method等等。

#### 全局事件总线-任意组件间通信

在vue中，可以使用**全局事件总线**实现任意组件间通信。使用全局事件总线之前首先要安装全局事件总线，通常是绑定在一个vc或者vm实例上，如下

```js
new Vue({
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this   // 安装全局事件总线（标准做法）
  }
}).$mount('#app')
```

在接收数据的组件中，按照如下方式定义：在组件中绑定事件，并编写事件的回调函数代码

```js
  receiveData(data) {
      console.log('School Component receive ', data)
    }
  },
  mounted() {
    this.$bus.$on('hello', this.receiveData)
  },
```

在发送数据的组件中，使用如下代码触发事件，发送数据

```js
this.$bus.$emit('hello', this.name)
```

**注意：**在接收数据的组件中，全局事件总线在**mounted**的时候挂载。

#### pubsub-js 消息订阅与发布

pubsub-js的原理和全局事件总线相同，是全局事件总线的一个封装库。在使用的时候不需要创建vc或vm实例充当全局事件总线，直接引入库即可

```js
npm install pubsub-js
```

引入库

```
import pubsub from 'pubsub-js'
```

在消息的接收方，在mounted函数中使用`pubsub.subscribe()`订阅消息

```
mounted() {
    this.pubId = pubsub.subscribe('hello', this.receiveData)
  },
  beforeDestroy() {
    pubsub.unsubscribe(this.pubId)
  }
```

在消息的发送方，使用`pubsub.publish`发布消息

```
sendStudentName() {
      pubsub.publish('hello', this.name)
    }
```

**注意：**引入的pubsub是一个对象，且在消息订阅时接收的第一个参数是函数名，第二个参数才是传过来的数据。

### 3.6 mixin属性

在vue中，如果需要在多个组件中使用相同的配置，则可以使用mixin将其定义为一个混合对象，在各个组件中引入即可。在使用混合mi xin时，可以全局使用，也可以局部使用。

### 3.7 Vue.use() 插件

在vue中，经常会在**main.js**文件中看到这样的代码

```javascript
Vue.use()
```

上述代码的作用是使用一个插件（可以是第三方的，也可以是自己造的）。

在vue中，插件用于增强vue。其本质是包含install()方法的一个对象，其中第一个参数是Vue，后面的参数是用户传入的参数。

### 3.8 scoped 样式

在vue中，所有的css代码最终都被汇总到一个文件中，为了防止样式名冲突，可以采用scoped，让样式在局部生效。代码如下

```html
<style scoped>

</style>
```

style中的lang参数指定用哪种方式写样式代码，默认是css，其他的还有less方式。

### 3.9 浏览器本地存储

浏览器的本地存储有两种，分别是localStorage和sessionStorage，这两种统称为webStorage。两种方式的区别是：

- localStorage存储的内容，需要手动清除才会消失，关闭浏览器不会消失

- sessionStorage存储的内容会随着浏览器窗口的关闭而消失

### 3.10 this.$nextTick()

在vue中，`this.$nextTick(回调函数)`用于在下一次DOM更新结束后执行其指定的回调函数。

**什么时候用？**

在vue中，只有一个函数全部执行完毕后，vue才会去更新DOM元素。但是在实际中，有这样一种情况，我们在函数中更改了某些数据，导致页面的DOM元素发生了改变，接着我们想基于更新后的DOM去进行一些操作，但是由于此时函数并没有执行完毕，直接编写后续代码不会奏效。这时候可以将具体操作放在`this.$nextTick()`中，它的作用就是告诉vue：你先去更新页面DOM元素，等你更新完了之后再来调用我。

### 3.11 配置代理

由于浏览器的数据请求遵循同源协议：即遵循的协议、主机名、端口号都相同，才会拿到返回数据。比如

前端服务器：http://localhost:8080

后端服务器：http://localhost:80

前端和后端服务器的端口号不同，违背了同源协议，浏览器会拦截后端返回的数据，即产生了跨域问题。跨域问题的报错信息如下

Access to XMLHttpRequest at 'http://192.168.31.137:8080/sockjs-node/info?t=1644160162236' from origin 'http://localhost:8080' has been blocked by **CORS** policy: No '**Access-Control-Allow-Origin**' header is present on the requested resource.

解决这个问题需要在vue.config.js文件中进行跨于配置（**方式一**）

```js
module.exports = {
    devServer: {
        proxy: 'http://localhost:80'
    }
}
```

其中的`proxy`是服务器地址，在发出请求的时候使用前端的服务器地址，如下

```js
getBooks() {
      axios.get('http://localhost:8080/books').then(
          response => {
            console.log('请求成功了', response.data)
          },
          error => {
            console.log('请求失败了', error.message)
          }
      )
```

在这里，http://localhost:8080是前端服务器的地址。

采用方式一的配置有以下两个限制：

1. 如果在前端服务器有着和后端服务器同样的路径资源，那么会请求前端服务器，而不是后端服务器。
2. 只能配置一个代理。如果项目中需要请求多个服务器，那么采取方式一就不行了。

针对以上限制，可以采用方式二进行配置，如下：

```js
devServer: {
        proxy: {
            '/api': {   // 匹配所有以/api开头的请求路径
                target: 'http://localhost:80',  // 代理目标的基础路径
                ws: true,   // 用于支持websocket
                changeOrigin: true, // 用于控制请求头中的host值，一般设为true即可
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
```

### 3.12 插槽 slot

插槽的作用：让父组件可以向子组件中的指定位置插入html结构，也是一种组件间通信的方式，适用于父组件==>子组件。

插槽的类型有：默认插槽、具名插槽、作用域插槽。

### 3.13 一些注意点

1. 在vue中，分为两部分：核心（core）+模版解析器（template），其中模版解析器在项目开发时起作用，在经过webpack打包之后就没用了。因此vue精简版去掉了模版解析器。所有带runtime的文件都是没有模版解析器的。

## 4. Vuex

### 4.1 基本概念

**概念：**vuex是专门在Vue中实现集中式状态（数据）管理的一个Vue插件，对Vue应用中的多个组件的共享状态进行集中式的管理（读/写），也是一种组件间通信方式，且适用于任意组件间通信。

**什么时候使用？**

当满足以下条件时使用vuex比较合理

1. 多个组件依赖于同一状态
2. 来自不同组件的行为需要变更同一状态

### 4.2 工作原理

Vuex中包含三个东西：**Actions**、**Mutations**、**State**。这三个东西都是一个对象，且都需要**store**的管理。

在Vuex中，数据存放在Vuex中的state中，组件通过{{$store.state}}来访问state中的数据，组件通过dispatch方法与actions进行对话，在actions中调用commit方法与mutations进行对话，在mutations中对数据进行操作。

如果业务逻辑比较简单，可以省略actions，在组件中调用commit方法直接对话mutations

```js
this.$store.commit('方法名', '参数')
```

### 4.3 搭建Vuex环境

安装Vuex

```
npm install vuex
```

在src目录下创建store/index.js文件，在其中配置store

```js
// 该文件用于创建Vuex中最为核心的store

import Vue from 'vue'
// 引入Vuex
import Vuex from "vuex";
// 应用Vuex插件
Vue.use(Vuex)

// 准备actions（用于响应组件中的动作）
const actions = {}
// 准备mutations（用于操作数据）
const mutations = {}
// 准备state（用于存储数据）
const state = {}


// 创建并暴露store
export default new Vuex.Store({
    actions,
    mutations,
    state
})
```

在main.js中创建vm实例，并引入store配置项

```js
import Vue from 'vue'
import App from './App.vue'

// 引入store
import store from "@/store";

// 关闭Vue的生产提示
Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  store
}).$mount('#app')

```

### 4.4 getters属性

当state中的数据需要进行加工后再使用的，可以使用getters加工，在store/index.js中配置getters配置项

```js
......

const getters = {
  bigSum(state) {
    return state.sum * 10
  }
}

export default new Vuex.Store({
  ......
  getters
})
```

在组件中使用getters配置的方法：$store.getters.bigSum

Vuex中的state和getters类似于Vue中的data和computed。前者是数据源，后者是操纵数据源。

### 4.5 未完待续

P112～P116视频还没看

## 5. Vue Router

Vue router是vue的一个插件库，专门用来实现单页面应用（SPA， single page web application）。

**单页面应用：**整个应用只有一个完整的页面，点击导航链接不会刷新页面，只会做页面的局部更新。

路由的本质上就是一组（key, value）键值对，在前端中，key是路径，value是组件。

### 5.1 基本使用

安装路由

```
npm install vue-router
```

安装指定版本的路由

```
npm install vue-router@3.2.0
```

**使用：**在main.js里面引入并使用

**配置：**在src/router/index.js中引入并编写配置，最后并暴露出去，在main.js里面引入并使用。

```js


/* 该文件专门用于创建整个应用的路由器（index.js） */

import VueRouter from "vue-router";
import Home from "@/components/Home";
import About from "@/components/About";

const router = new VueRouter({
    routes: [
        {
            path: '/home',
            component: Home
        },
        {
            path: '/about',
            component: About
        }
    ]
})

export default router

/* （main.js）*/

import Vue from 'vue'
import App from './App.vue'
// 引入VueRouter插件
import VueRouter from "vue-router";
import router from "@/router";


Vue.config.productionTip = false

// 使用VueRouter插件
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
```

**router-link**标签：用于实现路由的切换

**router-view**标签：用于指定组件呈现的位置

### 5.2 几个注意点

- 组件通常分为一般组件和路由组件，路由组件就是通过路由切换而展现在页面上的组件，一般放在`pages`文件夹下，而一般组件放在`components`文件夹下
- 通过路由切换，隐藏了的组件，默认是被销毁的，需要的时候再去挂载
- 每个组件都有自己的`$route`属性，里面存储着自己的路由信息
- 整个应用只有一个router，可以通过组件的`$router`属性获取到

### 5.3 嵌套（多级）路由-children

嵌套路由的配置规则，添加children配置项，需要注意的点有以下两个：

1. 在配置的children里面的路由配置项时，path前面不需要添加/
2. 在router-link跳转时，to=""需要填写完整路径（即：包括父亲的路径）

### 5.4 路由传参 - query属性

在进行路由跳转的时候可以携带参数，使用query传参，将参数携带在to属性中。其中有两种传递参数的写法

- 模版字符串写法
- 对象写法（传递参数过多时可以使用对象进行一个封装）

```vue
<ul>
      <li v-for="message in messageList" :key="message.id">
<!--        路由跳转携带参数，to的字符串写法-->
        <router-link
            :to="`/home/message/detail?id=${message.id}&title=${message.title}`">
          {{message.title}}
        </router-link>

<!--        路由跳转携带参数，to的对象写法-->
        <router-link :to="{
          path: '/home/message/detail',
          query: {
            id: message.id,
            title: message.title
          }
        }">
          {{message.title}}
        </router-link>
      </li>
    </ul>
```

在接收参数的时候使用

```vue
$route.query.id
$route.query.title
```

### 5.5 命名路由 - name属性

在进行路由跳转的时候，需要借助router-link标签中的to属性去配置路径path，这对于一些过长的路径显得比较繁琐，可以在配置路由的时候配置name属性，在router-link的to标签中使用name属性即可实现跳转。

填写完整路径的代码

```vue
        <router-link :to="{
          path: '/home/message/detail',
          query: {
            id: message.id,
            title: message.title
          }
        }">
```

使用name属性跳转的代码

```vue
        <router-link :to="{
          name: 'detail',
          query: {
            id: message.id,
            title: message.title
          }
        }">
```

### 5.6 路由- params

在路由传递参数的时候，除了query方式，还可以使用params方式，具体配置有如下几步

1. 在router/index.js下配置path参数，并添加占位符
2. 传递参数时使用params关键字（在router-link标签中）
3. 在接收参数时使用$route.params.

### 5.7 路由的props配置

用于传递参数时用props接收，简化代码，具体看视频。

### 5.8 router-link中的replace模式

router-link默认开启的是push模式，即浏览记录一直追加，栈。

在router-link标签中增加replace属性可以设置其开启replace模式，即每一次点击都是替换掉栈顶的浏览记录（无痕浏览是不是这样实现的？？？）

### 5.9 编程式路由导航

在之前的案例中，实现路由跳转都是借助router-link标签，还有一种方法可以不借助router-link标签实现路由跳转，称之为编程式路由导航。该方法可以使得路由跳转更加灵活。

具体有以下几个API（常用的）

- this.$router.push() —— 最常用
- this.$router.replace()
- this.$router.back()
- this.$router.forward()
- this.$router.go()

相关代码

```js
// 携带参数 name实现跳转
this.$router.push({
	name: 'detail',
          params: {
            id: message.id,
            title: message.title
          }
})

// path实现跳转
this.$router.push({
  path: '/detail'
})
```

### 5.10 keep-alive 缓存路由组件

组件的切换本质上是挂载与销毁的过程，如果不想某个组件被销毁，可以使用keep-alive标签将router-view标签包裹，并使用include属性指定哪个组件不被销毁。这称之为路由的缓存。代码如下

```vue
<keep-alive include="News">
          <router-view></router-view>
        </keep-alive>


<keep-alive :include="['News', 'Message']">
          <router-view></router-view>
        </keep-alive>
```

上述代码中include中的值为：组件名！！！缓存多个组件用数组即可。

### 5.11 路由中的生命周期钩子

在vue-router中有两个与路由有关的生命周期钩子，分别是

- activated：路由组件的激活时触发
- deactivated：路由组件的失活时触发

### 5.12 路由守卫

#### 全局前置路由守卫

全局前置路由守卫用于路由跳转权限控制，在初始化、以及每次**路由切换之前**被调用

参数含义如下

- from：从哪个页面来
- to：去往哪个页面
- next()：放行

```js
// 全局前置路由守卫（初始化的时候调用、每次路由切换之前调用）
router.beforeEach((to, from, next) => {
    console.log(to, from)
    if(to.name === 'news' || to.name === 'message') {
        if(localStorage.getItem('school') === 'atguigu'){
            next()
        } else {
            alert("学校名称有误，无权限查看！")
        }
    } else {
        next()
    }
})
```

在路由信息中，可以配置meta路由元信息，通过访问meta中的信息来决定该路由是否需要权限，代码如下

```js
{
                    name: 'news',
                    path: 'news',
                    component: News,
                    meta: {isAuth: true}
                },
                

// 全局前置路由守卫（初始化的时候调用、每次路由切换之前调用）
router.beforeEach((to, from, next) => {
    console.log(to, from)
    // if(to.name === 'news' || to.name === 'message') {
    if(to.meta.isAuth) {
        if(localStorage.getItem('school') === 'atguigu'){
            next()
        } else {
            alert("学校名称有误，无权限查看！")
        }
    } else {
        next()
    }
})
```

#### 全局后置路由守卫

全局后置路由守卫用于路由跳转权限控制，在初始化、以及每次**路由切换之后**被调用。一般用的不多，可以用来修改页面的title，参考如下代码

```js
// 全局后置路由守卫（初始化的时候调用、每次路由切换之后调用）
router.afterEach((to, from) => {
    console.log('后置路由', to, from)
    document.title = to.meta.title || 'vue_test'
})
```

#### 独享路由守卫

独享路由守卫是某一个路由所独享的，API如下

```js
							{
                    name: 'news',
                    path: 'news',
                    component: News,
                    meta: {isAuth: true, title: '新闻'},
                    beforeEnter(to, from, next) {
                        
                    }
                },
```

独享路由守卫只有前置，没有后置的。

#### 组件内路由守卫

在组件内也可以定义路由守卫，有以下两个API

- beforeRouterEnter(to, from, next)：通过**路由规则**，进入该组件时被调用
- beforeRouterLeave(to, from, next)：通过**路由规则**，离开该组件时被调用

### 5.13 路由器的两种工作模式

路由器有两种工作模式，分别是history和hash。

**hash模式：**hasn模式工作的路由器产生的路径中带有#，对于一个url来说，#及其后面的内容就是hash值。hash值不会包含在HTTP请求中，即：hash值不会带给服务器。兼容性较好。

**history模式：**的地址中不带#，地址美观干净，但是兼容性不如hash模式好。项目上线一般采用history模式，在项目上线时，需要借助后端人员的支持，解决刷新页面服务端404的问题。
