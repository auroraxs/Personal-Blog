<template>
    <div class="article" @click="view(blog)">
      <div class="article-cover">
        <img v-if="blog.titleImage" :src="blog.titleImage" style="width: 100%; height: 100%; border-radius: 5px">
        <img v-else src="../assets/imgs/cover.png" style="width: 100%; height: 100%; border-radius: 5px">
      </div>
      <div class="article-item-right">
        <div class="article-title">{{blog.title}}</div>
        <div class="article-info">
          <span>
            <i class="el-icon-date"></i> {{blog.createTime | formatDate}}
            <span class="separator">| </span>
          </span>
          <span>
            <i class="el-icon-document"></i>{{blog.category.name}}
          </span>
        </div>
        <div class="article-description">
          {{blog.description}}
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: "ArticleItem",
  props: ['blog'],
  data() {
    return {

    }
  },
  methods: {
    view(blog) {
      console.log('view方法被调用了')
      this.$router.push({name: 'Blog', params: {id: blog.id, blog: blog}})
    }
  },
  // 过滤器，用于格式化日期时间
  filters: {
    formatDate: function (dateTimeStr) {
      let dateTime = new Date(dateTimeStr)
      let YY = dateTime.getFullYear()
      let MM = dateTime.getMonth().toString().padStart(2, '0')
      let DD = dateTime.getDay().toString().padStart(2, '0')
      let hh = dateTime.getHours().toString().padStart(2, '0')
      let mm = dateTime.getMinutes().toString().padStart(2, '0')
      let ss = dateTime.getSeconds().toString().padStart(2, '0')
      return YY + "-" + MM + "-" + DD + " " + hh + ":" + mm + ":" + ss
    }
  }
}
</script>

<style scoped>
.article {
  display: flex;
  background-color: white;
  width: 730px;
  height: 200px;
  border: 1px solid white;
  margin: 10px 0;
  border-radius: 5px;
}

.article-cover {
  width: 270px;
}
.article-item-right {
  width: 440px;
  margin-left: 20px;
}
.article-title {
  font-size: 20px;
  text-align: center;
  margin: 20px 0;
}
.article-info {
  font-size: 13px;
  color: gray;
}
.article-description {
  margin-top: 10px;
  font-size: 14px;
  color: gray;
}
</style>