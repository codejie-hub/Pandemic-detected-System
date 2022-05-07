<template>
  <div>
    <el-row type="flex" :gutter="20">
      <!-- 添加确诊者信息 -->
      <el-col :span="6">
        <template>
          <div>
            <el-button
              type="primary"
              plain
              icon="el-icon-circle-plus-outline"
              @click="addCase"
            >添加感染者病例</el-button>
            <!-- 此处为添加表单 -->
            <el-dialog title="感染者信息" :visible.sync="FormVisible" :before-close="handleClose">
              <el-form
                ref="ruleForm"
                :model="ruleForm"
                :rules="addrules"
                label-position="left"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-form-item label="确诊者姓名" prop="name">
                  <el-input v-model="ruleForm.name" />
                </el-form-item>
                <el-form-item label="住址" prop="address">
                  <el-input v-model="ruleForm.address" />
                </el-form-item>

                <el-form-item label="城市" prop="city">
                  <el-input v-model="ruleForm.city" />
                </el-form-item>

                <el-form-item label="区域" prop="area">
                  <el-input v-model="ruleForm.area" />
                </el-form-item>

                <el-form-item label="确诊时间" required>
                  <el-col :span="11">
                    <el-form-item prop="date">
                      <el-date-picker
                        v-model="ruleForm.date"
                        type="date"
                        style="width: 100%"
                        value-format="yyyy-MM-dd"
                        format="yyyy 年 MM 月 dd 日"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                      />
                    </el-form-item>
                  </el-col>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                  <el-input v-model="ruleForm.phone" />
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                  <el-radio-group v-model="ruleForm.sex">
                    <el-radio label="男" name="sex" />
                    <el-radio label="女" name="sex" /> </el-radio-group></el-form-item>
                <el-form-item label="年龄" prop="age">
                  <el-input
                    v-model.number="ruleForm.age"
                    type="age"
                    autocomplete="off"
                  />
                </el-form-item>
                <el-form-item label="职业" prop="professional">
                  <el-input v-model="ruleForm.professional" />
                </el-form-item>
                <el-form-item label="接种疫苗" prop="vaccine">
                  <el-radio-group v-model="ruleForm.vaccine">
                    <el-radio label="是" name="vaccine" />
                    <el-radio label="否" name="vaccine" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="病例类型" prop="type">
                  <el-radio-group v-model="ruleForm.type">
                    <el-radio label="普通型" name="type" />
                    <el-radio label="轻型" name="type" />
                    <el-radio label="重型" name="type" />
                    <el-radio label="危重型" name="type" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="感染类型" prop="illtype">
                  <el-radio-group v-model="ruleForm.illtype">
                    <el-radio label="奥密克戎" />
                    <el-radio label="德尔塔" />
                    <el-radio label="其它" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="submitForm('ruleForm')"
                  >提交</el-button>
                  <el-button @click="resetForm('ruleForm')">取消</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
            <!-- 添加表单结束 -->
          </div>
        </template>
      </el-col>

      <!-- 导出excel表格数据 -->
      <el-col :span="6"> <el-button
        type="primary"
        plain
        icon="el-icon-upload2"
        @click="exportExcel"
      >导出数据</el-button></el-col>

      <!-- 刷新数据  -->
      <el-col :span="6">
        <el-button type="primary" plain icon="el-icon-loading" @click="refresh">刷新数据</el-button>
      </el-col>

      <!-- 批量删除 -->
      <el-col :span="6">
        <template>
          <el-popconfirm
            confirm-button-text="确定"
            cancel-button-text="取消"
            icon="el-icon-info"
            icon-color="red"
            title="确定删除吗？"
            @onConfirm="deleselect"
          >
            <el-button slot="reference" type="primary" plain icon="el-icon-circle-close">批量删除</el-button>
          </el-popconfirm>
        </template>
      </el-col>

      <el-row />
    </el-row>
    <!-- 确诊者名单 -->
    <el-row>
      <el-table
        v-loading="loading"
        :data="
          tableData.filter(
            (data) =>
              !search || data.name.toLowerCase().includes(search.toLowerCase())
          )
        "
        element-loading-text="数据加载中..."
        height="600"
        border
        highlight-current-row
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="40" />
        <el-table-column prop="cid" label="病例编号" sortable width="110" />
        <el-table-column prop="name" label="姓名" width="80" />
        <el-table-column prop="sex" label="性别" width="50" />

        <el-table-column prop="date" label="确诊日期" width="120" sortable />
        <el-table-column prop="age" label="年龄" width="50" />
        <el-table-column prop="phone" label="联系方式" width="120" />

        <el-table-column prop="address" label="地址" />
        <el-table-column prop="professional" label="职业" />
        <el-table-column prop="type" label="病情程度" width="80" />
        <el-table-column prop="illtype" label="感染类型" width="90" />
        <el-table-column prop="vaccine" label="是否接种疫苗" width="70" />

        <el-table-column align="center">
          <!-- <template slot="header" slot-scope="scope"> -->
          <template slot="header">
            <!-- 按name进行搜索 -->
            <el-input v-model="search" size="mini" placeholder="输入姓名搜索" />
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <!-- width="50%" -->
            <el-dialog
              title="编辑信息"
              :visible.sync="dialogVisible"
              :before-close="handleClose"
            >
              <el-form
                ref="modifyForm"
                :model="editForm"
                :rules="rules"
                label-position="left"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-form-item label="确诊者姓名" prop="name">
                  <el-input v-model="editForm.name" />
                </el-form-item>
                <el-form-item label="住址" prop="address">
                  <el-input v-model="editForm.address" />
                </el-form-item>

                <el-form-item label="确诊时间" required>
                  <el-col :span="11">
                    <el-form-item prop="date">
                      <el-date-picker
                        v-model="editForm.date"
                        type="date"
                        placeholder="选择日期"
                        style="width: 100%"
                        :picker-options="pickerOptions"
                      />
                    </el-form-item>
                  </el-col>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                  <el-input v-model="editForm.phone" />
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                  <el-radio-group v-model="editForm.sex">
                    <el-radio label="男" name="sex" />
                    <el-radio label="女" name="sex" /> </el-radio-group></el-form-item>
                <el-form-item label="年龄" prop="age">
                  <el-input
                    v-model.number="editForm.age"
                    type="age"
                    autocomplete="off"
                  />
                </el-form-item>
                <el-form-item pro="professional" label="职业">
                  <el-input v-model="editForm.professional" />
                </el-form-item>
                <el-form-item label="接种疫苗" prop="vaccine">
                  <el-radio-group v-model="editForm.vaccine">
                    <el-radio label="是" name="vaccine" />
                    <el-radio label="否" name="vaccine" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="病例类型" prop="type">
                  <el-radio-group v-model="editForm.type">
                    <el-radio label="普通型" name="type" />
                    <el-radio label="轻型" name="type" />
                    <el-radio label="重型" name="type" />
                    <el-radio label="危重型" name="type" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="感染类型" prop="illtype">
                  <el-radio-group v-model="editForm.illtype">
                    <el-radio label="奥密克戎" />
                    <el-radio label="德尔儿塔" />
                  </el-radio-group>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="submitForm('modifyForm')"
                  >提交</el-button>
                  <el-button @click="cancelForm('modifyForm')">取消</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>

            <template>
              <el-popconfirm
                confirm-button-text="确定"
                cancel-button-text="取消"
                icon="el-icon-info"
                icon-color="red"
                title="确定删除该病例信息吗？"
                @onConfirm="handleDelete(scope.$index, scope.row)"
              >
                <el-button
                  slot="reference"
                  size="mini"
                  type="danger"
                >删除</el-button>
              </el-popconfirm>
            </template>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import { deleCase } from '@/api/table'
import { addCase } from '@/api/table'
import { Message } from 'element-ui'
import { updateCase } from '@/api/table'
export default {
  name: 'Table',
  components: {},
  data() {
    return {
      ruleForm: {}, // 添加表单数据
      tableData: [], // this.$store.getters.caselist,
      multipleSelection: [], // 选中的数据对象
      editForm: {}, // 要编辑的病例对象
      search: '', // 搜索
      loading: false,
      FormVisible: false, // 添加表单是否显示
      dialogVisible: false, // 编辑表单是否显示
      // 添加表单约束
      addrules: {
        name: [
          { required: true, message: '请输入确诊者姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        vaccine: [
          { required: true, message: '请填写是否接种疫苗', trigger: 'change' }
        ],
        address: [
          { required: true, message: '请填写地址信息', trigger: 'blur' }
        ],

        city: [{ required: true, message: '请填写城市信息', trigger: 'blur' }],
        area: [{ required: true, message: '请填写区域信息', trigger: 'blur' }],
        age: [
          { required: true, message: '年龄不能为空' },
          { type: 'number', message: '年龄必须为数字' }
        ],
        phone: [
          {
            required: true,
            message: '请输入电话号码',
            trigger: 'blur'
          },
          {
            min: 11,
            max: 11,
            message: '请输入正确位数的号码',
            trigger: 'blur'
          }
        ],
        date: [
          {
            type: 'string',
            required: true,
            message: '请选择日期',
            trigger: 'change'
          }
        ],
        type: [
          {
            // type: "array",
            required: true,
            message: '请选择病例类型',
            trigger: 'change'
          }
        ],
        illtype: [
          { required: true, message: '请选择感染类型', trigger: 'change' }
        ],
        professional: [
          { required: true, message: '职业不能为空', trigger: 'blur' }
        ]
      },
      // 编辑表单约束
      rules: {
        name: [
          { required: true, message: '请输入确诊者姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        vaccine: [
          { required: true, message: '请填写是否接种疫苗', trigger: 'change' }
        ],
        address: [
          { required: true, message: '请填写地址信息', trigger: 'blur' }
        ],
        // region: [
        //   { required: true, message: "请选择活动区域", trigger: "change" },
        // ],
        age: [
          { required: true, message: '年龄不能为空' },
          { type: 'number', message: '年龄必须为数字' }
        ],
        phone: [
          {
            required: true,
            message: '请输入电话号码',
            trigger: 'blur'
          },
          {
            min: 11,
            max: 11,
            message: '请输入正确位数的号码',
            trigger: 'blur'
          }
        ],
        date1: [
          {
            type: 'date',
            required: true,
            message: '请选择日期',
            trigger: 'change'
          }
        ],
        type: [
          {
            // type: "array",
            required: true,
            message: '请选择病例类型',
            trigger: 'change'
          }
        ],
        illtype: [
          { required: true, message: '请选择感染类型', trigger: 'change' }
        ]
        // desc: [{ required: true, message: "请填写活动形式", trigger: "blur" }],
      },
      // // 时间选择配置
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            }
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }
        ]
      }
    }
  },
  computed: {
    // data初始化在计算属性之前
    getList() {
    // eslint-disable-next-line indent
    console.log('computed - getlist')
      console.log(this.$store.getters.caselist)
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      this.tableData = this.$store.getters.caselist
      return true
    }
  },
  mounted() {
    this.fetchData()
    console.log('store.caselist=')
    console.log(this.$store.getters.caselist)
    this.getList
  },
  created() {

  },
  methods: {
    // 当选择项发生变化时会触发该事件
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log('val =')
      console.log(val)
    },
    // 编辑
    handleEdit(index, row) {
      this.dialogVisible = true
      // row为要编辑信息的对象
      this.editForm = row
    },
    // 删除,row指所要删除的数组中的对应对象
    handleDelete(index, row) {
      // console.log(index, row)
      // 查找指定元素在tableData中的位置索引
      index = this.tableData.indexOf(row)
      // 请求后端删除,后端按照cid删除
      console.log('要删除的cid为')
      console.log(row.cid)
      deleCase(row.cid).then(response => {
        Message({
          type: 'success',
          message: response.message,
          duration: 3000
        })
        // 从数组中移除此元素
        this.tableData.splice(index, 1)
        // 删除store中对应的病例
        this.$store.dispatch('cases/removecase', index)
      }).catch(error => {
        console.log(error)
        Message({
          type: 'error',
          message: '删除失败',
          duration: 1000
        })
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭?')
        .then((_) => {
          done()
        })
        .catch((_) => {})
    },
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var form = {} // 提交的表单对象
          if (formName === 'ruleForm') {
            form = this.ruleForm
            // 向后端发送请求，添加病例
            addCase(form).then(response => {
              console.log(form)
              Message({
                message: response.message || '添加成功',
                type: 'success',
                duration: 1000
              })
              this.$refs[formName].resetFields()
              this.FormVisible = false
            }).catch(error => {
              console.log(error)
              // 失败提醒
              Message({
                type: 'error',
                message: '添加失败',
                duration: 1000
              })
            })
          } else if (formName === 'modifyForm') { // 修改相应的病例对象
            updateCase(this.editForm).then(response => {
              Message({
                message: response.message || '更新成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
            }).catch(error => {
              Message({
                type: 'error',
                message: '更新失败',
                duration: 1000
              })
              console.log('error=')
              console.log(error)
            })
          }
        } else {
          this.$message({
            message: '提交错误！',
            type: 'warning'
          })
        }
        return false
      })
    },
    cancelForm(formName) {
      console.log('formname is' + formName)
      this.dialogVisible = false
    },
    // 获取病例数据
    fetchData() {
      this.loading = true
      this.$store.dispatch('cases/fetchcases').then(() => {
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
      this.loading = false
    },
    //
    addCase() {
      this.FormVisible = true
    },
    exportExcel() {
      const download = document.createElement('a')
      download.href = 'http://localhost:8089/excel/export'
      // document.body.appendChild(download);
      download.click()
      // document.body.removeChild(download);
      download.remove()
    },
    // 刷新数据
    refresh() {
      this.fetchData()
      this.tableData = this.$store.getters.caselist
    },
    // 批量删除数据,删除选中的数据
    deleselect() {
      if (this.multipleSelection.length === 0) {
        Message({
          type: 'warning',
          message: '请先选择要删除的数据项',
          duration: 1000
        })
      } else {
        // 批量删除
        var tag = true // 批量删除成功标志 默认成功
        for (var obj = 0; obj < this.multipleSelection.length; obj++) {
          deleCase(this.multipleSelection[obj].cid).then(response => {
            console.log(response.message)
          }).catch(error => {
            tag = false
            Message({ type: 'error', message: '删除失败', duration: 1000 })
            console.log(error)
          })
          // 从数组中移除相应对象
          const index = this.tableData.indexOf(this.multipleSelection[obj])
          this.tableData.splice(index, 1) // 移除该病例对象
        }
        if (tag) {
          Message({
            type: 'success',
            message: '批量删除成功',
            duration: 1000
          })
        }
      }
    },
    // 重置添加病例
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.FormVisible = false
    }
  }
}
</script>
<style lang="css"></style>
