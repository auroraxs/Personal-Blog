<template>
  <div class="blog-box">
    <div class="base-info">
      <h1>{{blog.title}}</h1>
      <div style="font-size: 15px">
        <span>
        <i class="el-icon-time" /> 发表于 {{blog.createTime | formatDate}} |
      </span>
        <span>
        <i class="el-icon-document"></i> {{blog.category.name}} |
      </span>
        <span>
        <i class="el-icon-view" /> 阅读量: {{blog.views}}
      </span>
      </div>
    </div>
    <div class="content">
      <div
          v-html="markdownToHtml()"
      ></div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import MarkdownIt from 'markdown-it'
// 引入highlight.js, 代码高亮库
import hljs from 'highlight.js'
// 引入highlight.js 中的css样式
import 'highlight.js/styles/default.css'

export default {
  name: "Blog",
  data() {
    return {
      blog: {}
    }
  },
  created() {
    this.loadDataByIdFromPath()
    this.markdownToHtml()
  },
  methods: {
    // 根据path中的id向后端请求博客数据
    loadDataByIdFromPath() {
      let blog_id = this.$route.path.split("/")[2]
      request.get("/blogs/" + blog_id).then(
          response => {
            this.blog = response.data.data.blog
            console.log(this.blog)
            this.updateViews()
          }
      )
    },
    // markdown文本转html
    markdownToHtml() {
      let md = new MarkdownIt({
        html: true,
        linkify: true,
        typographer: true,
        highlight: function (str, lang) {
          if (lang && hljs.getLanguage(lang)) {
            try {
              return '<pre class="hljs"><code>' +
                  hljs.highlight(str, {language: lang, ignoreIllegals: true}).value +
                  '</code></pre>'
            } catch(_) {}
          }
        }
      })
      return md.render(this.blog.content)
    },
    // 更新浏览量views
    updateViews() {
      let form = {id: this.blog.id, views: this.blog.views + 1}
      request.post("/blogs", form).then(
          response => {
            console.log(response)
          }
      )
    }
  },
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
.blog-box {
  min-height: calc(100vh - 120px - 50px - 100px);
  width: 60%;
  margin: 50px auto;
}

.base-info {
  text-align: center;
  margin: 30px 0 60px 0;
  color: palevioletred;
}

.content {
  background-color: white;
  color: dimgray;
  font-family: "Arial", sans-serif;
  border: 1px solid white;
  border-radius: 10px;
  padding: 0 20px;
}

</style>