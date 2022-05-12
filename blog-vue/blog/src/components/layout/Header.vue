<template>
  <div class="header-container">
    <div class="header-left">摸、鱼</div>
    <div style="flex: 1"></div>
    <div class="header-right">
<!--      使用vue-router模式，在激活导航时以index作为path进行路由跳转 -->
      <el-menu
          :default-active="path"
          router
          mode="horizontal"
          background-color="#f3eae6"
          active-text-color="black"
          style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; color: #ccc"
      >
        <el-menu-item style="height: 50px; line-height: 50px" index="/">首页</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/category">分类</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/archive">归档</el-menu-item>
        <el-menu-item style="height: 50px; line-height: 50px" index="/about">关于</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      path: this.$route.path
    }
  },
}
</script>

<style scoped>
.header-container {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #cccccc;
  display: flex;
}
.header-left {
  width: 200px;
  padding-left: 50px;
  font-weight: bold;
  font-size: 25px;
  color: dodgerblue;
}
.header-right {
  width: 500px;
}
</style>