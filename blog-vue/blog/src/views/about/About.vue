<template>
  <div class="container">
    <div class="content-box">
      <div class="subtitle">关于我</div>
      <hr>
      <div class="text">
        程序员界的摸鱼大师一枚，简称鱼哥～<br/>
        目前就读于南京大学，是一名CV方向的研究僧。主要的研究方向为：三维人脸人体重建(3D Reconstruction)、
        神经辐射场(Neural Radiance Field, NeRF)。在科研之余对开发有一些浅尝辄止的了解，热衷于技术且乐于交流分享。
      </div>
      <div class="subtitle">关于本站</div>
      <hr/>
      <div class="text">
        本站所采用的技术和整体结构都比较简单，只是用来展示和分享一些文章。如果您渴望拥有一个酷炫、好看的个人主页，本站可能无法满足您的需求。
        网站整体上是基于前后端分离的模式搭建的，前端采用vue框架，后端采用springboot框架。所涉及的一些技术细节如下
        <ul>
          <li>springboot</li>
          <li>mybatis</li>
          <li>maven</li>
          <li>vue、vue router</li>
          <li>axios</li>
          <li>element ui</li>
        </ul>
        换言之，如果你想复现本站，那么以上内容还是需要学习一下滴。无需深入，会用即可～
      </div>
      <div class="subtitle">我在哪里</div>
      <hr/>
      <div>
        <a class="iconfont iconfont-GitHub logo"
           style="color: gray"
           href="https://github.com/xusensun"
           target="_blank"
        ></a>
        <a class="iconfont iconfont-gitee logo"
           style="color: #b7312d;"
           href="https://gitee.com/xusensun"
           target="_blank"
        ></a>
        <a class="iconfont iconfont-bilibili logo"
           style="color: #ea7a99"
           href="https://space.bilibili.com/406322374"
           target="_blank"
        ></a>
        <a class="iconfont iconfont-shejiaotubiao-46 logo"
           style="color: #2568f6;"
           href="https://www.zhihu.com/people/sun-xu-sen-30"
           target="_blank"
        ></a>
        <a class="iconfont iconfont-csdn logo"
           style="color: #e96040"
           href="https://i.csdn.net/#/user-center/profile?spm=1001.2101.3001.5111"
           target="_blank"
        ></a>
      </div>
      <div class="text" style="margin: 10px 0">
        平时会在知乎、CSDN发一些技术类文章，本站也会同步更新。为了观感更佳，大家可以在上述平台观看，毕竟本站目前还略显简陋......
        另外，喜欢指弹的朋友可以在B站联系我哦～
      </div>
      <div class="subtitle">联系我</div>
      <hr/>
      <div class="text" style="height: 30px; line-height: 30px; font-size: 15px">
        <a class="iconfont iconfont-QQ logo"
           style="color: skyblue; font-size: 14px"
           target="_blank"
        ></a>: 2909300605
      </div>
      <div class="text" style="height: 30px; line-height: 30px; font-size: 15px">
        <a class="iconfont iconfont-youxiang logo"
           style="color: purple; font-size: 14px"
           target="_blank"
        ></a>: xusensuncv@smail.nju.edu.cn
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "About"
}
</script>

<style scoped>

.container {
  min-height: calc(100vh - 120px - 50px - 40px);
  background: white;
  width: 800px;
  margin: 20px auto;
  border-radius: 10px;
}

.content-box {
  padding: 10px 20px;
}

.subtitle {
  font-size: 1.3em;
  margin: 20px 0 10px 0;
}

.logo {
  font-size: 30px;
  margin: 20px;
  text-decoration: none;
}

.text {
  color: dimgray;
  font-size: 15px;
  line-height: 22px;
}
</style>