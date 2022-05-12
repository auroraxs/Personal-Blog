import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/home/Home";
import Category from "@/views/category/Category";
import Archive from "@/views/archive/Archive";
import About from "@/views/about/About";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/category',
    name: 'Category',
    component: Category
  },
  {
    path: '/archive',
    name: 'Archive',
    component: Archive
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/blog/:id',
    name: 'Blog',
    component: () => import('../views/blog/Blog')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
