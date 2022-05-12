<template>
  <div class="container">
    <div class="archive-card">
      <div
          v-for="year in years"
      >
        <h3 style="color: darkturquoise">{{year}}</h3>
        <hr>
        <div
            v-for="blog in archive[year]"
            style="color: dimgray"
        >
          {{blog.createTime | formatDate}} &nbsp;&nbsp;&nbsp; {{blog.title}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Archive",
  data() {
    return {
      years: [],
      archive: {}
    }
  },
  created() {
    this.loadAllBlog()
  },
  methods: {
    loadAllBlog() {
      request.get("/blogs").then(
          response => {
            let blogs = response.data.data.blogs
            this.archiveByYear(blogs)
          }
      )
    },
    archiveByYear(blogs) {
      let archive_dict = {}
      blogs.forEach(function (blog) {
        let year = blog.createTime.slice(0, 4)
        if(archive_dict.hasOwnProperty(year)) {
          archive_dict[year].push(blog)
        } else {
          archive_dict[year] = [blog]
        }
      })
      let years = Object.keys(archive_dict).sort(function (a, b) {
        return a > b ? -1 : a < b ? 1 : 0
      })
      this.years = years
      this.archive = archive_dict
    }
  },
  filters: {
    formatDate: function (dateTimeStr) {
      let dateTime = new Date(dateTimeStr)
      let YY = dateTime.getFullYear()
      let MM = dateTime.getMonth().toString().padStart(2, '0')
      let DD = dateTime.getDay().toString().padStart(2, '0')
      return YY + "-" + MM + "-" + DD
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 120px - 50px);
}

.archive-card {
  width: 800px;
  min-height: 400px;
  margin: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 10px;
}

</style>