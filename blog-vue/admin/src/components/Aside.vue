<template>
  <el-menu :default-openeds="['1', '2']" style="height: 100%; overflow-x: hidden;"
             background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
              router
    >
      <!--          添加后台管理系统的logo :TODO relative属性再了解-->
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/logo.png" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
        <b style="color: white" v-show="logoTextShow">后台管理系统</b>
      </div>


      <el-menu-item index="/">
        <template slot="title">
          <i class="el-icon-house"></i>
          <span slot="title">主页</span>
        </template>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/manager/user">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span slot="title">用户管理</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/manager/blog">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span slot="title">博客管理</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/manager/category">
            <template slot="title">
              <i class="el-icon-collection-tag"></i>
              <span slot="title">分类管理</span>
            </template>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>

</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
    asideWidth: Number
  }
}
</script>

<style scoped>

</style>