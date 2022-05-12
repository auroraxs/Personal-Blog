<template>
  <el-container>
    <el-main>
      <div style="min-height: 80%">
        <BlogItem
            v-for="blog in blogs"
            :key="blog.id"
            :blog="blog"
        >
        </BlogItem>
      </div>

      <div>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[2, 5, 10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </el-main>
    <el-aside style="width: 270px">
      <div style="position: fixed">
        <AuthorInfo
            :blogNum="total"
            :categoryNum="categoryNum"
        >
        </AuthorInfo>
        <WebsiteInfo/>
      </div>
    </el-aside>
  </el-container>
</template>

<script>

import BlogItem from "@/components/BlogItem";
import AuthorInfo from "@/components/card/AuthorInfo";
import request from "@/utils/request";
import WebsiteInfo from "@/components/card/WebsiteInfo";

export default {
  name: "Home",
  components: {WebsiteInfo, AuthorInfo, BlogItem},
  data() {
    return {
      blogs: [],
      currentPage: 1,
      pageSize: 5,
      total: 0,
      categoryNum: 0,
      totalViews: 0,
    }
  },
  created() {
    this.loadBlogData()
    this.loadCategoryData()
  },
  methods: {
    loadBlogData() {
      request.get("/blogs/", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
      }).then(
          response => {
            this.blogs = response.data.data.blogList
            this.total = response.data.data.count
          }
      )
    },
    loadCategoryData() {
      request.get("/categories").then(
          response => {
            this.categoryNum = response.data.data.categoryList.length
          }
      )
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.loadBlogData()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.loadBlogData()
    }
  }
}
</script>

<style scoped>
.el-container {
  width: 1020px;
  min-height: 100%;
  margin: 10px auto;
}
.el-main {
  padding: 10px 0;
}
.el-aside {
  margin-left: 10px;
  margin-top: 20px;
}

</style>