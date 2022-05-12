<template>
  <div>
    <!--          引入搜索框-->
    <div style="padding: 10px 0">
      <el-input
          v-model="username"
          style="width: 200px"
          suffix-icon="el-icon-search"
          placeholder="请输入用户名"
          class="ml-5">
      </el-input>
      <el-input
          v-model="role"
          style="width: 200px"
          suffix-icon="el-icon-user"
          placeholder="请输入角色"
          class="ml-5">
      </el-input>
      <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
    </div>

    <!--          引入增删改查按钮-->
    <div style="margin: 10px">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline el-icon--right"></i></el-button>
      <el-button type="danger">批量删除<i class="el-icon-remove-outline el-icon--right"></i></el-button>
      <el-button type="primary">导入<i class="el-icon-bottom el-icon--right"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top el-icon--right"></i></el-button>
    </div>

    <!--          TODO: stripe border 属性-->
    <el-table :data="tableData" stripe border>
      <el-table-column prop="id" label="ID" width="260"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <!--            <el-table-column prop="avatar" label="头像"></el-table-column>-->
      <el-table-column prop="role" label="角色"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="success"
              @click="handleEdit(scope.$index, scope.row)">编辑
            <i class="el-icon-edit el-icon--right"></i>
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            <i class="el-icon-delete el-icon--right"></i>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--          引入分页组件-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="用户名" >
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" >
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" >
          <el-input v-model="form.role" autocomplete="off"></el-input>
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
  name: "User",
  data() {
    return {
      // 表格数据及分页 属性
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      // 搜索框 相关属性
      username: '',
      role: '',
      // 新增｜编辑 按钮 相关属性
      dialogFormVisible: false,
      form: {
        username: "",
        password: "",
        nickname: "",
        role: ""
      }
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    // 向后端服务器请求数据并加载
    loadData() {
      request.get("/users/", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          role: this.role
        }
      }).then(
          response => {
            this.tableData = response.data.data.userList
            this.total = response.data.data.total
            console.log("loadData()被调用了")
          },
          error => {
            console.log(error)
          }
      )
    },
    // 分页查询（页大小改变）触发事件
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.loadData()
    },
    // 分页查询（当前页改变）触发事件
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.loadData()
    },
    // 搜索按钮触发事件
    handleSearch() {
      this.loadData()
    },
    // 新增按钮触发事件
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    // 对话框取消按钮触发事件
    handleCancel() {
      this.dialogFormVisible = false
      this.form = {}
    },
    // 对话框确认按钮触发事件
    handleConfirm() {
      request.post("/users", this.form).then(
          response => {
            if (response.data.state === "200") {
              this.$message.success("操作成功")
              this.loadData()
            }
          }
      )
      this.dialogFormVisible = false
    },
    // 表格中编辑按钮触发事件
    handleEdit(index, row) {
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    // 表格中删除按钮触发事件
    handleDelete(index, row) {
      let userId = row.id
      request.delete("/users/" + userId).then(
          response => {
            if(response.data.state === "200") {
              this.$message.success("删除成功")
              this.loadData()
            }
          },
          error => {
            console.log(error)
          }
      )
    }
  }
}
</script>

<style scoped>

</style>