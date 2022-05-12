<template>
  <div>
<!--    搜索框组件行-->
    <div style="margin: 10px">
      <el-input
          style="width: 300px"
          placeholder="请输入博客名称"
          suffix-icon="el-icon-search"
          v-model="searchTitle"
      >
      </el-input>
      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
    </div>

<!--    增删改查组件行-->
    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增博客<i class="el-icon-circle-plus el-icon--right"></i></el-button>
    </div>

<!--    表格组件-->
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="user.username" label="作者" width="100px"></el-table-column>
      <el-table-column prop="views" label="浏览次数" width="80px"></el-table-column>
      <el-table-column prop="category.name" label="分类" width="100px"></el-table-column>
<!--      <el-table-column label="标签" width="200px">-->
<!--        <template slot-scope="scope">-->
<!--          <div style="display: flex">-->
<!--            <div class="box-tag" v-for="tag in scope.row.tags">{{tag.name}}</div>-->
<!--          </div>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column prop="createTime" label="创建时间" width="160px">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="published" label="是否发表" width="80px"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit el-icon--right"></i></el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Blog",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      searchTitle: ''
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    loadData() {
      request.get("/blogs/", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          title: this.searchTitle
        }
      }).then(
          response => {
            this.tableData = response.data.data.blogList
            this.total = response.data.data.count
          }
      )
    },
    handleAdd() {
      this.$router.push({name: "BlogDetail", params: {id: "-1"}})
    },
    handleEdit(index, row) {
      this.$router.push({name: "BlogDetail", params: {id: row.id}})
      console.log(row)
    },
    handleDelete(index, row) {
      let blogId = row.id
      request.delete("/blogs/" + blogId).then(
          response => {
            if(response.data.state === "200") {
              this.$message.success("删除成功！")
              this.loadData()
            }
          },
          error => {
            this.$message.error("操作失败！")
          }
      )
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.loadData()
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.loadData()
    },
    handleSearch() {
      this.loadData()
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
.box-tag {
  border: #606266 1px solid;
  margin: 3px;
  background-color: #606266;
  color: white;
  padding: 1px 2px;
  border-radius: 3px;
}

</style>