<template>
  <transition>
    <div @click="toTop" v-show="topShow" class="me-to-top">
      <i class="el-icon-caret-top"></i>
    </div>
  </transition>
</template>

<script>
export default {
  name: "BackToTop",
  data() {
    return {
      topShow: false
    }
  },
  methods: {
    toTop() {
      document.documentElement.scrollTop = 0
      this.topShow = false
    },
    needToTop() {
      // 获取滚动条的位置
      let currentHeight = document.documentElement.scrollTop
      this.topShow = currentHeight > 400;
    }
  },
  mounted() {
    // 等到页面渲染完成之后给window添加一个监听事件（用于监听滚动条的位置，来判断是否需要触发needToTop函数）
    this.$nextTick(function () {
      window.addEventListener('scroll', this.needToTop)
    })
  }
}
</script>

<style scoped>
.me-to-top {
  background-color: #fff;
  position: fixed;
  right: 100px;
  bottom: 150px;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  /*鼠标放到该位置显示为一个手指的形状*/
  cursor: pointer;
  transition: 0.3s;
  /*给div元素添加阴影*/
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
  z-index: 5;
}
.me-to-top i {
  color: skyblue;
  display: block;
  /*line-height和height相同，控制元素垂直居中*/
  line-height: 40px;
  /*控制元素水平居中*/
  text-align: center;
  font-size: 18px;
}
</style>