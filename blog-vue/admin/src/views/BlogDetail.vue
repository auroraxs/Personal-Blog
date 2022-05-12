<template>
  <div>
    <div class="title-box">
      <el-input placeholder="请输入博客标题" v-model="form.title">
        <template slot="prepend">标题</template>
      </el-input>
    </div>
    <div class="title-box">
      <el-input placeholder="请输入题图链接" v-model="form.titleImage">
        <template slot="prepend">题图<i class="el-icon-link el-icon--right"></i> </template>
      </el-input>
    </div>
    <mavon-editor v-model="form.content" style="min-height: 500px"/>

    <div class="bottom-box">
      <el-select
          class="mr-5"
          v-model="categoryId"
          filterable
          allow-create
          default-first-option
          placeholder="请选择博客分类">
        <el-option
            v-for="item in optionsCategory"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <div style="flex: 1"></div>
      <el-button type="primary" @click="handleSave('否')">保存为草稿</el-button>
      <el-button type="primary" @click="handleSave('是')">发表</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "BlogDetail",
  data() {
    return {
      categoryId: '',
      optionsCategory: [],
      form: {
        title: '',
        description: '',
        content: '',
        views: 0,
        published: '',
        titleImage: '',
        user: {'id': '1', name: '摸鱼大大'},
        category: {},
      }
    }
  },
  created() {
    this.loadCategory()
    this.loadBlogDataByIdFromPath()
    // console.log(this.form)
    // console.log(this.optionsCategory)
  },
  methods: {
    // 初始化界面，加载分类数据
    loadCategory() {
      request.get("/categories").then(
          response => {
            this.optionsCategory = response.data.data.categoryList
          }
      )
    },
    loadBlogDataByIdFromPath() {
      // 根据参数是否包含blog来判断是新增博客还是编辑博客
      let blog_id = this.$route.params.id
      if (blog_id !== '-1') {
        request.get("/blogs/" + blog_id).then(
            response => {
              let blog = response.data.data.blog
              this.form = blog
              this.categoryId = blog.category.id
            }
        )
      }
    },
    // 保存并发表
    handleSave(published) {
      let newCategoryFlag = !this.isNewCategory()

      if(newCategoryFlag) {
        this.form.category = {"name": this.categoryId}
      } else {
        this.updateCategory()
      }
      this.form.published = published
      this.form.description = this.form.content.slice(0, 100) + "..."
      console.log(this.form.description)

      request.post("/blogs", this.form).then(
          response => {
            if(response.data.state === '200') {
              this.$message.success("保存成功！")
              this.$router.push("/manager/blog")
            }
          },
          error => {
            this.$message.error("保存失败！")
          }
      )
    },
    // 判断当前分类是否为一个新的分类
    isNewCategory() {
      for(let i=0; i<this.optionsCategory.length; i++) {
        if(this.categoryId === this.optionsCategory[i].id) {
          return true
        }
      }
      return false
    },
    // 根据绑定在select选择器中的categoryId更新form中的category
    updateCategory() {
      for(let i=0; i<this.optionsCategory.length; i++) {
        if(this.categoryId === this.optionsCategory[i].id) {
          this.form.category = this.optionsCategory[i]
          return
        }
      }
    }
  }
}
</script>

<style scoped>
.title-box {
  width: 500px;
  margin: 10px auto;
}

.bottom-box {
  display: flex;
  margin: 10px 0;
}

</style>