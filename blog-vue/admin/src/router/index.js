import Vue from 'vue'
import VueRouter from 'vue-router'
import Manager from '../views/Manager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: Manager,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/Home')
      },
      {
        path: 'manager/user',
        name: 'User',
        component: () => import('../views/User')
      },
      {
        path: 'manager/blog',
        name: 'Blog',
        component: () => import('../views/Blog')
      },
      {
        path: 'manager/category',
        name: 'Category',
        component: () => import('../views/Category')
      },
      {
        // 定义:id占位符，根据博客id来指定动态路由
        path: 'detail/:id',
        name: 'BlogDetail',
        component: () => import('../views/BlogDetail'),
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
