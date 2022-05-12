<template>
  <el-footer style="height: 120px">
    <div class="footer-wrap">
      <div style="margin-bottom: 10px">©2019 - {{ new Date().getFullYear() }} By 摸丶鱼</div>
      <a href="https://beian.miit.gov.cn/" target="_blank" style="color: white; text-decoration: none">
        湘ICP备19021924号
      </a>
    </div>
  </el-footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.el-footer {
  background: linear-gradient(-45deg, #ee7752, #ce3e75, #23a6d5, #23d5ab);
}
.footer-wrap {
  text-align: center;
  position: relative;
  font-size: 13px;
  color: white;
  top: 30%;
}
</style>