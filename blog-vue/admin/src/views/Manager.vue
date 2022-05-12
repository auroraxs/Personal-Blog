<template>
  <div style="height: 100%;">
    <el-container style="height: 100%;">

      <el-aside :width="asideWidth + `px`" style="background-color: rgb(238, 241, 246); height: 100%;">
        <Aside :width="asideWidth"
               :isCollapse="isCollapse"
               :logoTextShow="logoTextShow"
        />
      </el-aside>

      <el-container>
<!--        :TODO 将header设置为flex布局，flex布局再了解-->
        <el-header style="border-bottom: 1px solid #ccc;">
          <Header :collapseBtnClass="collapseBtnClass"
                  :collapse="collapse"
          />
        </el-header>

        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manager',
  components: {Header, Aside},
  data() {
    return {
      // Header 相关属性
      collapseBtnClass: 'el-icon-s-fold',
      // Aside 相关属性
      isCollapse: false,
      asideWidth: 200,
      logoTextShow: true,
    }
  },
  methods: {
    // 控制Aside隐藏、展开
    collapse() {
      this.isCollapse = !this.isCollapse
      if(this.isCollapse) {
        this.asideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {
        this.asideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>
