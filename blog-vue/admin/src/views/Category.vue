<template>
  <div>

    <div style="margin: 10px">
      <el-input
          style="width: 300px"
          placeholder="请输入分类名称"
          suffix-icon="el-icon-search"
          v-model="searchName"
      >
      </el-input>
      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
    </div>

    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增分类<i class="el-icon-circle-plus el-icon--right"></i></el-button>
    </div>

    <!--    表格组件-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit el-icon--right"></i></el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Category",
  data() {
    return {
      tableData: [],
      form: {
        name: ''
      },
      dialogFormVisible: false,
      currPage: 1,
      pageSize: 5,
      total: 0,
      searchName: ''
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    loadData() {
      request.get("/categories/", {
        params: {
          currPage: this.currPage,
          pageSize: this.pageSize,
          categoryName: this.searchName
        }
      }).then(
          response => {
            console.log(response)
            this.tableData = response.data.data.categoryList
            this.total = response.data.data.count
          }
      )
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleCancel() {
      this.dialogFormVisible = false
      this.form = {}
    },
    handleConfirm() {
      this.dialogFormVisible = false
      request.post("/categories", this.form).then(
          response => {
            if(response.data.state === '200') {
              this.$message.success("操作成功")
              this.loadData()
            }
          }
      )
    },
    handleEdit(index, row) {
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    handleDelete(index, row) {
      let categoryId = row.id
      request.delete("/categories/" + categoryId).then(
          response => {
            if(response.data.state === '200') {
              this.$message.success("删除成功")
              this.loadData()
            }
          },
          error => {
            this.$message.error("当前分类下存在博客，无法删除该分类！")
          }
      )
    },
    handleCurrentChange(val) {
      this.currPage = val
      this.loadData()
    },
    handleSearch() {
      this.loadData()
    }
  }
}
</script>

<style scoped>

</style>