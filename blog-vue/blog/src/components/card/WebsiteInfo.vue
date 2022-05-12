<template>
  <div class="website-card">
    <div class="icon-title">
      <i class="el-icon-sunny" style="font-size: 18px"/> 网站资讯
    </div>
    <div class="detail-info">
      <div>运行时间: {{ runTime }}</div>
      <div style="margin-top: 5px">总浏览量: {{ totalViews }}</div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "WebsiteInfo",
  data() {
    return {
      runTime: '',
      totalViews: 0,
      startDate: '2022-04-27 00:00:00'
    }
  },
  created() {
    this.calcTotalViews()
  },
  mounted() {
    this.timer = setInterval(this.refreshRunTime, 1000)
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  methods: {
    refreshRunTime() {
      let time = Date.now() - Date.parse(this.startDate)
      console.log(time)
      let dd = Math.floor(time / (24 * 60 * 60 * 1000))
      let hh = Math.floor((time - dd * (24 * 60 * 60 * 1000)) / (60 * 60 * 1000))
      let mm = Math.floor((time - dd * (24 * 60 * 60 * 1000) - hh * (60 * 60 * 1000)) / (60 * 1000))
      let ss = Math.floor((time - dd * (24 * 60 * 60 * 1000) - hh * (60 * 60 * 1000) - mm * (60 * 1000)) / 1000)
      this.runTime = dd + "天" + hh + "时" + mm + "分" + ss + "秒"
    },
    calcTotalViews() {
      request.get("/blogs").then(
          response => {
            let blogs = response.data.data.blogs
            let total = 0
            blogs.forEach(function (blog) {
              total += blog.views
            })
            this.totalViews = total
          }
      )
    }
  }
}
</script>

<style scoped>
.website-card {
  width: 270px;
  height: 140px;
  margin-top: 20px;
  background-color: white;
  border-radius: 10px;
}

.icon-title {
  font-size: 18px;
  padding: 20px;
}

.detail-info {
  padding-left: 20px;
  font-size: 14px;
  color: dimgray;
}

</style>