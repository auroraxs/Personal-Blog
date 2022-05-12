<template>
  <div class="author-card">
    <div class="author-img">
      <img src="../../assets/imgs/author.png" style="width: 100%; height: 100%">
    </div>
    <div>
      <div style="font-size: 30px">摸、鱼</div>
      <div style="margin: 10px 0">你 来 啦 ？</div>
    </div>
    <div class="blog-info">
      <div>
        <div class="text-item">文章</div>
        <div>{{blogNum}}</div>
      </div>
      <div>
        <div class="text-item">分类</div>
        <div>{{categoryNum}}</div>
      </div>
      <div>
        <div class="text-item">标签</div>
        <div>12</div>
      </div>
    </div>
    <div class="fellow-me"> Follow me </div>
    <div class="contact-me">
      <a class="iconfont iconfont-GitHub"
         style="font-size: 30px"
         href="https://github.com/xusensun"
         target="_blank"
      ></a>
      <a class="iconfont iconfont-bilibili"
         style="font-size: 30px"
         href="https://space.bilibili.com/406322374"
         target="_blank"
      ></a>
      <a class="iconfont iconfont-gitee"
         style="font-size: 30px"
         href="https://gitee.com/xusensun"
         target="_blank"
      ></a>
    </div>
  </div>
</template>

<script>
export default {
  name: "AuthorInfo",
  props: ['blogNum', 'categoryNum']
}
</script>

<style scoped>
.author-card {
  width: 270px;
  height: 350px;
  background-color: white;
  border-radius: 10px;
  text-align: center;
}
.author-img {
  width: 100px;
  height: 100px;
  margin: 0 auto;
  padding-top: 5px;
}
.blog-info {
  display: flex;
  justify-content: space-between;
  margin: 10px 50px;
}
.text-item {
  margin: 10px 0;
}
.fellow-me {
  height: 30px;
  width: 240px;
  background-color: #49b1f5;
  line-height: 30px;
  color: white;
  margin: 0 auto;
}

.contact-me {
  margin: 10px 0;
}
.contact-me a {
  margin: 0 20px;
  text-decoration: none;
  color: gray;
}
</style>